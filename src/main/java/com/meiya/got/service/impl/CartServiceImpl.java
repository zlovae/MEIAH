package com.meiya.got.service.impl;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.service.ICartService;
import com.meiya.got.util.JedisUtil;
import com.meiya.got.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private JedisUtil jedisUtil;

//    @Override
//    public ServerResponse addItem(Long userId,Long storeId, Long productId) {
//        try {
//            String key = RedisKeyUtil.getCartKey(userId, storeId);
//            String field = RedisKeyUtil.getCartField(productId);
//
//            jedisUtil.hincrby(key, field);
//            return ServerResponse.createBySuccessMessage("添加购物车成功");
//        } catch (Exception e) {
//            return ServerResponse.createByErrorMessage("添加购物车失败");
//        }
//    }
//
//    @Override
//    public ServerResponse deleteItem(Long userId, Long storeId, Long productId) {
//        try {
//            String key = RedisKeyUtil.getCartKey(userId, storeId);
//            String field = RedisKeyUtil.getCartField(productId);
//
//            jedisUtil.hdecrby(key, field);
//            return ServerResponse.createBySuccessMessage("删除成功");
//        } catch (Exception e) {
//            return ServerResponse.createByErrorMessage("删除失败");
//        }
//    }

    @Override
    public ServerResponse delAll(String phone, Long storeId) {
        try {
            String key = RedisKeyUtil.getCartKey(phone, storeId);
            jedisUtil.delall(key);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }
//
//    @Override
//    public ServerResponse check(Long userId, Long productId) {
//        Cart cart = cartDAO.selectByUF(userId, productId);
//        try {
//            cartDAO.setCheck(cart.getId());
//            return ServerResponse.createBySuccess();
//        } catch (Exception e) {
//            return ServerResponse.createByError();
//        }
//    }
//
//    @Override
//    public ServerResponse unCheck(Long userId, Long productId) {
//        Cart cart = cartDAO.selectByUF(userId, productId);
//        try {
//            cartDAO.setUnCheck(cart.getId());
//            return ServerResponse.createBySuccess();
//        } catch (Exception e) {
//            return ServerResponse.createByError();
//        }
//    }
//
//    @Override
//    public ServerResponse checkAll(Long userId) {
//        List<Cart> Carts = cartDAO.getAllById(userId);
//        try {
//            for (Cart cart : Carts) {
//                cartDAO.setCheck(cart.getId());
//            }
//            return ServerResponse.createBySuccess();
//        } catch (Exception e) {
//            return ServerResponse.createByError();
//        }
//    }
//
//    @Override
//    public ServerResponse unCheckAll(Long userId) {
//        List<Cart> Carts = cartDAO.getAllById(userId);
//        try {
//            for (Cart cart : Carts) {
//                cartDAO.setUnCheck(cart.getId());
//            }
//            return ServerResponse.createBySuccess();
//        } catch (Exception e) {
//            return ServerResponse.createByError();
//        }
//    }
}

