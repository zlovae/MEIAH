package com.meiya.got.controller;

import com.meiya.got.common.Const;
import com.meiya.got.common.ResponseCode;
import com.meiya.got.common.ServerResponse;
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

import javax.servlet.http.HttpSession;
@CrossOrigin
@Controller
@RequestMapping("/api/")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderServiceImpl iOrderService;

    @Autowired
    private UserServiceImpl iUserService;


    @PostMapping("cart")
    @ResponseBody
    public ServerResponse setCart(@RequestParam("phone")String phone ,@RequestParam("sid")Long storeid,@RequestParam("fid")Long foodid,@RequestParam("num")Integer quantity) {
        return iOrderService.setCart(phone, storeid, foodid, quantity);
    }

    @PutMapping("order")
    @ResponseBody
    public ServerResponse createOrder(@RequestParam("phone")String phone, @RequestParam("sid")Long storeId) {
        return iOrderService.createOrder(phone, storeId);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public ServerResponse orderDetail(Long orderId, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user==null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            return iOrderService.orderDetail(user.getId(), orderId);
        }
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

    @RequestMapping("/pay")
    @ResponseBody
    public ServerResponse payOrder(@RequestParam("order_id") Long orderId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        if(user==null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            return iOrderService.payOrder(user.getId(), orderId);
        }
    }

    @RequestMapping("/callback")
    @ResponseBody
    public ServerResponse orderCallback(Long orderId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        if(user==null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            return iOrderService.payOrder(user.getId(), orderId);
        }
    }

    @RequestMapping("/status")
    @ResponseBody
    public ServerResponse payStatus(Long orderId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        if(user==null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            return iOrderService.payOrder(user.getId(), orderId);
        }
    }

}
