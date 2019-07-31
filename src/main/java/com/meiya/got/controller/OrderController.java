package com.meiya.got.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.meiya.got.common.Const;
import com.meiya.got.common.ResponseCode;
import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.OrderDAO;
import com.meiya.got.po.Order;
import com.meiya.got.po.PayInfo;
import com.meiya.got.po.User;
import com.meiya.got.service.IOrderService;
import com.meiya.got.service.IUserService;
import com.meiya.got.service.impl.OrderServiceImpl;
import com.meiya.got.service.impl.UserServiceImpl;
import com.meiya.got.util.JedisUtil;
import com.meiya.got.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/api/")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderServiceImpl iOrderService;

    @Autowired
    private UserServiceImpl iUserService;

    @Autowired
    private OrderDAO orderDAO;

    /**@function 修改购物车
     * @return success/error
     * @param phone
     * @param storeid
     * @param foodid
     * @param quantity
     * */
    @PostMapping("cart")
    @ResponseBody
    public ServerResponse setCart(@RequestParam("phone")String phone ,
                                  @RequestParam("sid")Long storeid,
                                  @RequestParam("fid")Long foodid,
                                  @RequestParam("num")Integer quantity) {
        return iOrderService.setCart(phone, storeid, foodid, quantity);
    }

    /**@function 提交购物车并转到支付页面
     * @return Order
     * @param phone
     * @param storeId
     * */
    @PutMapping("order")
    @ResponseBody
    public ServerResponse createOrder(@RequestParam("phone")String phone, @RequestParam("sid")Long storeId) throws Exception {
        return iOrderService.createOrder(phone, storeId);
    }

    @RequestMapping("/cancel")
    @ResponseBody
    public ServerResponse cancel(Long orderId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        if(user==null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            return iOrderService.cancelOrder(user.getId(), orderId);
        }
    }

    //TODO: 分页
    @RequestMapping("/list")
    @ResponseBody
    public ServerResponse list(Long orderId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        if(user==null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            return iOrderService.cancelOrder(user.getId(), orderId);
        }
    }

    /**@function 转到支付页面
     * @return order(付款码)
     * @param orderId
     * @param address
     * @param notes
     * */
    @RequestMapping("order/pay")
    @ResponseBody
    public ServerResponse payOrder(@RequestParam("order_id") Long orderId,
                                   @RequestParam("address")String address,
                                   @RequestParam("notes")String notes,
                                   HttpSession httpSession) {
        Integer mid=1;
        Order order = orderDAO.selectByOrderNo(orderId);
        order.setAddress(address);
        order.setNote(notes);
        orderDAO.updateByKey(order);
        switch (mid) {
            case 1 :
                return iOrderService.aliPay(order.getUser_id(), orderId);
            default :
                return ServerResponse.createByErrorMessage("不支持的支付方式");
        }
    }

    @RequestMapping("order/callback")
    @ResponseBody
    public Object orderCallback(HttpServletRequest request) throws Exception {
        Map<String, String> params = Maps.newHashMap();

        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {

                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        logger.info("支付宝回调,sign:{},trade_status:{},参数:{}", params.get("sign"), params.get("trade_status"), params.toString());

        //非常重要,验证回调的正确性,是不是支付宝发的.并且呢还要避免重复通知.

        params.remove("sign_type");
        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "utf-8", Configs.getSignType());

            if (!alipayRSACheckedV2) {
                return ServerResponse.createByErrorMessage("非法请求,验证不通过,再恶意请求我就报警找网警了");
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝验证回调异常", e);
        }

        //验证各种数据
        ServerResponse serverResponse = iOrderService.orderCallBack(params);
        if (serverResponse.isSuccess()) {
            return Const.AlipayCallback.RESPONSE_SUCCESS;
        }
        return Const.AlipayCallback.RESPONSE_FAILED;
    }

    @RequestMapping("status")
    @ResponseBody
    public int queryOrderPayStatus(@RequestParam("uid") Long uid, @RequestParam("oid") Long oid) throws Exception {
        ServerResponse serverResponse = iOrderService.orderStatus(uid, oid);
        if (serverResponse.isSuccess()) {
            return 1;
        }
        return 0;
    }

    @RequestMapping("historyorder")
    @ResponseBody
    public ServerResponse queryOrderList(@RequestParam("uid") Long uid) {
        ServerResponse serverResponse = iOrderService.orderList(uid);
        if (serverResponse.isSuccess()) {
            return serverResponse;
        }
        return ServerResponse.createByError();
    }

    @RequestMapping("order/items")
    @ResponseBody
    public ServerResponse queryOrderItem(@RequestParam("oid") Long oid) {
        ServerResponse serverResponse = iOrderService.orderDetail(oid);
        if (serverResponse.isSuccess()) {
            return serverResponse;
        }
        return ServerResponse.createByError();
    }

    /**
     * @function 再来一单
     * @return List<OrderItem>;
     * @param oid
     * */
    @RequestMapping("order/onemore")
    @ResponseBody
    public ServerResponse orderOneMore(@RequestParam("oid") Long oid) {
        ServerResponse serverResponse = iOrderService.orderOneMore(oid);
        if (serverResponse.isSuccess()) {
            return serverResponse;
        }
        return ServerResponse.createByError();
    }

    /**
     * @function 获取当前用户点的最多菜的菜系的所有商家
     * @return List<OrderItem>;
     * @param userId
     * */
    @RequestMapping("order/pickstore")
    @ResponseBody
    public ServerResponse pickOneStore(@RequestParam("uid") Long userId) {
        ServerResponse serverResponse = iOrderService.pickOneStore(userId);
        if (serverResponse.isSuccess()) {
            return serverResponse;
        }
        return ServerResponse.createByError();
    }

//    @RequestMapping("order/refund")
//    @ResponseBody
//    public ServerResponse orderRefund(@RequestParam("oid") Long oid) {
//        ServerResponse serverResponse = iOrderService.
//        if (serverResponse.isSuccess()) {
//            return serverResponse;
//        }
//        return ServerResponse.createByError();
//    }
}
