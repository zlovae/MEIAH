package com.meiya.got;

import java.util.*;
import com.meiya.got.dao.OrderDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderDAO orderDAO;

    private Jedis jedis;

    //@Test
   // public static long getOrderId() {
//设置订单号前缀（可以根据数据环境/地区的不同来设置不同订单号前缀）
        //String ordernoIndex = PropertiesManager.getPropertiesValue("key");//从配置中心获取订单号前缀
        //if(StringUtils.isBlank(ordernoIndex)){
          //  ordernoIndex = "";
        //}
//从redis中获取订单号
        //long orderId = .incr(REDIS_ORDER_KEY, CART_REDIS_POOL);
//判断订单号是否小于订单号初始值
        //if(orderId < orderIdInitValue){
 //设置redis中orderNo的初始值
          //  JedisManager.setString(REDIS_ORDER_KEY, String.valueOf(orderIdInitValue), 0, CART_REDIS_POOL);
            //orderId = JedisManager.incr(REDIS_ORDER_KEY, CART_REDIS_POOL);
        //}
//拼接订单号（订单前缀+redis中的自增长值），并返回
        //return Long.valueOf(new StringBuffer().append(ordernoIndex).append(String.valueOf(orderId)).toString());
   // }

    public static void main(String[] args) {

    }

}
