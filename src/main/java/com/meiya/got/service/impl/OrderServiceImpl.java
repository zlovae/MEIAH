package com.meiya.got.service.impl;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayMonitorServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.google.common.collect.Lists;
import com.meiya.got.common.Const;
import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.*;
import com.meiya.got.po.*;
import com.meiya.got.sender.SendTests;
import com.meiya.got.service.IOrderService;
import com.meiya.got.util.BigDecimalUtil;
import com.meiya.got.util.JedisUtil;
import com.meiya.got.util.RedisKeyUtil;
import com.meiya.got.vo.SeckillVo;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements IOrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Autowired
    private UserDAO userDAO;

//    @Autowired
//    private CartDAO cartDAO;

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private FoodsDAO foodsDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderItemDAO orderItemDAO;

//    @Autowired
//    private FanoutSender fanoutSender;
    @Autowired
    private SendTests sendTests;

    @Autowired
    private PayInfoDAO payInfoDAO;

    @Autowired
    private SeckillDAO seckillDAO;

    // 支付宝当面付2.0服务
    private static AlipayTradeService   tradeService;

    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
    private static AlipayTradeService   tradeWithHBService;

    // 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
    private static AlipayMonitorService monitorService;

    static {
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

        /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
                .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK")
                .setFormat("json").build();
    }

    @Override
    public ServerResponse setCart(String phone, Long storeId, Long foodId, Integer quantity) {
        try {
            String key = RedisKeyUtil.getCartKey(phone, storeId);
            String field = RedisKeyUtil.getCartField(foodId);
            jedisUtil.hset(key, field, String.valueOf(quantity));
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            System.out.println(logger.toString());
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse<Order> createOrder(String phone, Long storeId, Integer payMethod) throws Exception {
        ServerResponse response = this.getCartItem(phone, storeId);

        if(!response.isSuccess()) {
            return response;
        }

        List<OrderItem> orderItemList = (List<OrderItem>) response.getData();
        System.out.println(orderItemList);
        BigDecimal payment = this.getOrderTotalPrice(orderItemList);

        //生成订单
        Long userId = userDAO.getByPhone(phone).getId();
        Order order = this.assembleOrder(userId, storeId, payment);
        if (order == null) {
            return ServerResponse.createByErrorMessage("生成订单错误");
        }
        if (CollectionUtils.isEmpty(orderItemList)) {
            return ServerResponse.createByErrorMessage("购物车为空");
        }
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrder_id(order.getId());
        }
        //mybatis 批量插入
        orderItemDAO.batchInsert(orderItemList);
        System.out.println(orderItemList);
        //生成成功,我们要减少我们产品的库存
        this.reduceProductStock(orderItemList);
        System.out.println("减去库存成功");
        /********************因为测试时候不想一直添加购物车就注释掉了***************/
        jedisUtil.delall(RedisKeyUtil.getCartKey(phone, storeId));
        System.out.println("删除购物车成功");
        System.out.println(order.getId());
        MsgConnection msgConnection = new MsgConnection(order.getId(), 0, 1, order.getStore_id(), order.getUser_id());
        sendTests.sendToStore(msgConnection);
        switch (payMethod) {
            case 1 : return this.aliPay(userId, order.getId());
            //case 2 : return this.meiyaPay(userId, order.getId());
        }

        return ServerResponse.createByError();
        //返回给前端数据
        //OrderVo orderVo = assembleOrderVo(order, orderItemList);
        //return ServerResponse.createBySuccess(order);
    }

    private void reduceProductStock(List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            Foods food = foodsDAO.selectById(orderItem.getProduct_id());
            Integer stock = food.getStock() - orderItem.getQuantity();
            foodsDAO.updateStock(food.getId(), stock);
        }
    }

    private Order assembleOrder(Long userId,Long storeId, BigDecimal payment) {
        System.out.println(userId);
        Order order = new Order();
        long orderNo = this.generateOrderNo();
        order.setId(orderNo);

        order.setStore_id(storeId);
        order.setStatus(Const.OrderStatusEnum.NO_PAY.getCode());
        //order.setPostage(0);
        order.setComment_status(0);
        order.setPayment_type(Const.PaymentTypeEnum.NOT_PAY.getCode());
        order.setPayment(payment);

        order.setUser_id(userId);
        //发货时间等等
        order.setCreate_time(new Date());
        //付款时间等等
        int rowCount = orderDAO.insert(order);
        if (rowCount > 0) {
            order.setId(orderNo);
            return order;
        }
        return null;
    }

    private BigDecimal getOrderTotalPrice(List<OrderItem> orderItemList) {
        BigDecimal payment = new BigDecimal("0");
        for (OrderItem orderItem : orderItemList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getTotal().doubleValue());
        }
        return payment;
    }

    public ServerResponse getCartItem(String phone, Long storeId) {
        //List<Cart> cartList = cartDAO.selectByChecked(userId);
        String key = RedisKeyUtil.getCartKey(phone, storeId);

        Map<String, String> cartMap = jedisUtil.getall(key);

        System.out.println(key);

        if(cartMap.isEmpty()) {
            return ServerResponse.createByErrorMessage("购物车为空，先去选购吧！");
        }

        List<OrderItem> orderItemList = new ArrayList<>();

        for(Map.Entry<String, String> m : cartMap.entrySet()) {
            Long foodsId = Long.valueOf(m.getKey());
            Integer quantity = Integer.valueOf(m.getValue());

            OrderItem orderItem = new OrderItem();
            Foods food = foodsDAO.selectById(foodsId);

            if(food.getStatus()!= Const.ProductStatusEnum.ON_SALE.getCode()) {
                return ServerResponse.createByErrorMessage("菜品" + food.getName() + "不在售卖状态");
            }
            if(food.getStock() < quantity) {
                return ServerResponse.createByErrorMessage("菜品" + food.getName() + "库存不足");
            }

            orderItem.setName(food.getName());
            orderItem.setPhoto(food.getPhoto());
            orderItem.setQuantity(quantity);
            orderItem.setProduct_id(foodsId);
            Long userId = userDAO.getByPhone(phone).getId();
            orderItem.setPrice(food.getPrice());
            orderItem.setTotal(BigDecimalUtil.mul(food.getPrice().doubleValue(), quantity));
            orderItemList.add(orderItem);
        }
        return ServerResponse.createBySuccess(orderItemList);
    }



    /*
    * 生成订单表中的明细，并写入数据库
    * */
    public ServerResponse<List<OrderItem>> generateOrderItem(List<Cart> cartList, Long order_id) {
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            for (Cart cart : cartList) {
                Foods food = foodsDAO.selectById(cart.getProduct_id());
                OrderItem orderItem = new OrderItem();

                orderItem.setName(food.getName());
                orderItem.setOrder_id(order_id);
                orderItem.setProduct_id(food.getId());
//                orderItem.setUser_id(cart.getUser_id());
//                orderItem.setStore_id(food.getStore_id());
                orderItem.setPhoto(food.getPhoto());
                orderItem.setPrice(food.getPrice());
                orderItem.setQuantity(cart.getQuantity());
                orderItem.setTotal(food.getPrice().multiply(new BigDecimal(cart.getQuantity())));
//                orderItem.setCreat_time(new java.util.Date());
//                orderItem.setUpdate_time(new java.util.Date());
                orderItemList.add(orderItem);
            }
            return ServerResponse.createBySuccess(orderItemList);
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    private long generateOrderNo() {
        long currentTime = System.currentTimeMillis();
        return currentTime + new Random().nextInt(100);
    }
//
//    @Override
//    public ServerResponse<Order> confirmOrder(Long userId, Long orderId) {
//        //TODO
//        return null;
//    }

    public ServerResponse aliPay(Long userId, Long orderId) {

        //String path = "E:/IdeaProjects/MY-GOT/QRCodeImge";

        Map<String, String> resultMap = new HashMap<>();
        Order order = orderDAO.selectByUserIdAndOrderNo(userId, orderId);
        if(order==null) {
            return ServerResponse.createByErrorMessage("订单不存在！");
        }

        //处理订单
        resultMap.put("orderNo: ", String.valueOf(order.getId()));

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        //String outTradeNo = "tradeprecreate" + System.currentTimeMillis() + (long) (Math.random() * 10000000L);
        String outTradeNo = order.getId().toString();

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = "美亚点餐扫码消费";

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = order.getPayment().toString();

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = "共计花费"+ totalAmount + "元";

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = order.getStore_id().toString();

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        //GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000, 1);
        // 创建好一个商品后添加至商品明细列表
        //goodsDetailList.add(goods1);

        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
        //GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        //goodsDetailList.add(goods2);


        System.out.println(order.getId());
        List<OrderItem> orderItemList = orderItemDAO.selectByOrderId(orderId);
        System.out.println(orderItemList.size());
        for (OrderItem orderItem : orderItemList) {
            //goodsDetail = GoodsDetail.newInstance("food_id" + orderItem.getProduct_id().toString(), "food_name" + orderItem.getName(), orderItem.getPrice().longValue()*100, orderItem.getQuantity());
            GoodsDetail goodsDetail = GoodsDetail.newInstance("11", "name", 200, 2);
            goodsDetailList.add(goodsDetail);
        }
        if(goodsDetailList.isEmpty())
            System.out.println("null!!!");


        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                .setNotifyUrl("http://47.103.118.92:8443/api/order/callback")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);

        System.out.println(builder);
        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);


        System.out.println(result.getTradeStatus());
        switch (result.getTradeStatus()) {
            case SUCCESS:
                logger.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                // 需要修改为运行机器上的路径
                //String filePath = String.format("vue_demo\\static\\images\\QrImage\\qr-%s.png",
                        //response.getOutTradeNo());
                //logger.info("filePath:" + filePath);
                //ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                order.setStatus(20);
                order.setQr_code(response.getQrCode());
                orderDAO.updateByKey(order);
                //TODO 二维码存缓存并返回给客户端
                return ServerResponse.createBySuccess(order);

            case FAILED:
                logger.error("支付宝预下单失败!!!");
                break;

            case UNKNOWN:
                logger.error("系统异常，预下单状态未知!!!");
                break;

            default:
                logger.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return ServerResponse.createByErrorMessage(logger.toString());
    }

    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            logger.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                logger.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            logger.info("body:" + response.getBody());
        }
    }

//    public ServerResponse meiyaPay(Long userId, Long orderId) {
//
//    }

    @Override
    public ServerResponse cancelOrder(Long userId, Long orderId) {
        Order order = orderDAO.selectByOrderNo(orderId);
        if(order==null) {
            return ServerResponse.createByErrorMessage("订单不存在！");
        }
        switch (order.getStatus()) {
            case 0 :
                return ServerResponse.createByErrorMessage("订单已取消");
            case 10 :
                order.setStatus(0);
                return ServerResponse.createBySuccessMessage("订单取消成功");
            case 20 :
                return ServerResponse.createByErrorMessage("订单已付款，不能取消");
            case 40:
                return ServerResponse.createByErrorMessage("订单正在配送，不能取消");
            case 50:
                return ServerResponse.createByErrorMessage("交易完成，不能取消");
            case 60 :
                return ServerResponse.createByErrorMessage("交易关闭，不能取消");
            default:
                return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse orderDetail(Long orderId) {
        List<OrderItem> orderItemList = orderItemDAO.selectItemByOrderId(orderId);
        if(orderItemList==null) {
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        return ServerResponse.createBySuccess(orderItemList);
    }

    @Override
    public ServerResponse<List<Order>> orderList(Long userId) {
        //TODO ordervo
        List<Order> orderList = orderDAO.selectAllOrder(userId);
        try {
            return ServerResponse.createBySuccess(orderList);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.toString());
        }
    }

    @Override
    public ServerResponse orderCallBack(Map<String, String> params) throws Exception {
        //TODO
        Long orderNo = Long.parseLong(params.get("out_trade_no"));
        String tradeNo = params.get("trade_no");
        String tradeStatus = params.get("trade_status");
        Order order = orderDAO.selectByOrderNo(orderNo);
        if (order == null) {
            return ServerResponse.createByErrorMessage("回调忽略");
        }
        if (order.getStatus() >= Const.OrderStatusEnum.PAID.getCode()) {
            return ServerResponse.createBySuccess("支付宝重复调用");
        }
        if (Const.AlipayCallback.TRADE_STATUS_TRADE_SUCCESS.equals(tradeStatus)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //order.setPayment_time(sdf.parse(params.get("gmt_payment")));
            order.setStatus(Const.OrderStatusEnum.PAID.getCode());
            orderDAO.updateByKey(order);
            //orderMapper.updateByPrimaryKeySelective(order);
        }

        PayInfo payInfo = new PayInfo();
        payInfo.setUser_id(order.getUser_id());
        payInfo.setOrder_id(order.getId());
        payInfo.setPlatform(Const.PayPlatformEnum.ALIPAY.getCode());
        payInfo.setSerial_number(tradeNo);
        payInfo.setStatus(tradeStatus);

        payInfoDAO.insert(payInfo);
        System.out.println(payInfo);

        MsgConnection msgConnection = new MsgConnection(order.getId(), 0, 2, order.getStore_id(), order.getUser_id());
        sendTests.sendToStore(msgConnection);
        return ServerResponse.createBySuccess();
        //return null;
    }

    @Override
    public ServerResponse orderStatus(Long userId, Long orderId) {
        //TODO
        Order order = orderDAO.selectByUserIdAndOrderNo(userId, orderId);
        if (order == null) {
            return ServerResponse.createByErrorMessage("用户没有该订单");
        }
        if (order.getStatus() >= Const.OrderStatusEnum.PAID.getCode()) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse orderOneMore(Long orderId) {
        //TODO 再来一单 根据订单号找到Item，把Item推到购物车
        try {
            Order order = orderDAO.selectByOrderNo(orderId);
            List<OrderItem> orderItemList = orderItemDAO.selectItemByOrderId(orderId);
            List<Cart> cartList = new ArrayList<>();
            String key = RedisKeyUtil.getCartKey(userDAO.getById(order.getUser_id()).getPhone(), order.getStore_id());

            for (OrderItem item : orderItemList) {
                Cart cart = new Cart();
                cart.setProduct_id(item.getProduct_id());
                cart.setName(item.getName());
                cart.setStore_id(order.getStore_id());
                BigDecimal price = foodsDAO.selectById(item.getProduct_id()).getPrice();
                cart.setPrice(price);
                Integer quantity = item.getQuantity();
                cart.setQuantity(quantity);
                cart.setTotal_price(price.multiply(BigDecimal.valueOf(quantity)));
                cartList.add(cart);
                String field = RedisKeyUtil.getCartField(item.getProduct_id());
                jedisUtil.hset(key, field, item.getQuantity().toString());
            }
            System.out.println(orderItemList);
            return ServerResponse.createBySuccess(cartList);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.toString());
        }
    }

    @Override
    public ServerResponse orderCommentStatus(Long orderId, Integer status) {
        try {
            Order order = orderDAO.selectByOrderNo(orderId);
            order.setComment_status(status);
            orderDAO.updateCommentStatus(order);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }
    @Transactional
    public ServerResponse seckill(Long userId, Long skId) {
        try {
            //修改库存
            seckillDAO.reduceStock(skId);
            System.out.println("修改库存成功");
            //生成订单
            SeckillVo seckillVo = seckillDAO.getByFoodId(skId);
            Foods food = foodsDAO.selectById(seckillVo.getGoods_id());
            Long orderNo = this.generateOrderNo();

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct_id(seckillVo.getSid());
            orderItem.setName(food.getName());
            orderItem.setPrice(seckillVo.getSeckill_price());//秒杀价格
            orderItem.setQuantity(1);
            orderItem.setTotal(seckillVo.getSeckill_price());
            orderItem.setPhoto(food.getPhoto());
            orderItem.setOrder_id(orderNo);
            orderItemDAO.insert(orderItem);
            System.out.println(orderItem);

            Order order = new Order();
            order.setId(orderNo);
            order.setStore_id(seckillVo.getSid());
            order.setComment_status(0);
            order.setPayment(seckillVo.getSeckill_price());
            order.setCreate_time(new Date());
            order.setPayment_type(Const.OrderStatusEnum.NO_PAY.getCode());
            order.setUser_id(userId);
            orderDAO.insert(order);
            System.out.println(order);

            System.out.println(userId + "" + orderNo);
            return this.aliPay(userId, orderNo);
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }
}
