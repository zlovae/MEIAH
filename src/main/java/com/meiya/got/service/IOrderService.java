package com.meiya.got.service;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.po.Cart;
import com.meiya.got.po.Order;
import com.meiya.got.po.PayInfo;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@Service
public interface IOrderService {


    /***************************************************用户端***************************************************/
    //修改购物车
    ServerResponse setCart(String phone, Long storeId, Long foodId, Integer quantity);
    //根据购物车中勾选的菜品
    //ServerResponse getCartItem(Long userId, Long storeId);
    //生成订单
    ServerResponse<Order> createOrder(String phone, Long storeId, Integer payMethod) throws Exception;
    //确认订单
    //ServerResponse<Order> confirmOrder(Long userId, Long orderId);
    //付款
    //ServerResponse<String> aliPay(Long userId, Long orderId);
    //取消订单
    ServerResponse cancelOrder(Long userId, Long orderId);
    //查看订单详情
    ServerResponse orderDetail(Long orderId);
    //查看订单列表
    ServerResponse orderList(Long userId);
    //支付宝回调
    ServerResponse orderCallBack(Map<String, String> params) throws Exception;
    //查看订单状态
    ServerResponse orderStatus(Long userId, Long orderId);

    ServerResponse orderOneMore(Long orderId);

    /****************************************************商户端**************************************************/


}
