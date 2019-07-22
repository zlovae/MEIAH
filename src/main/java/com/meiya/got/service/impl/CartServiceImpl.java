package com.meiya.got.service.impl;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.CartDAO;
import com.meiya.got.po.Cart;
import com.meiya.got.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.sql.Date;
import java.util.List;

//import java.sql.Date;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartDAO cartDAO;

    @Override
    public ServerResponse getCart(Long userId) {
        List<Cart> cartList = cartDAO.getAllById(userId);
        if(cartList!=null) {
            return ServerResponse.createBySuccess(cartList);
        } else {
            return ServerResponse.createBySuccessMessage("获取购物车数据失败");
        }
    }

    @Override
    public ServerResponse<String> addItem(Long userId, Long productId, Integer quantity) {
        Cart cart = cartDAO.selectByUF(userId, productId);
        try {
            if(cart==null) {
                cart=new Cart();
                cart.setUser_id(userId);
                cart.setProduct_id(productId);
                //cart.setCreat_time(time);
                //cart.setUpdate_time(time);
                cart.setCheck(1);
                cart.setQuantity(quantity);
                cartDAO.insertItem(cart);
            } else {
                cart.setQuantity(cart.getQuantity()+quantity);
            }
            return ServerResponse.createBySuccessMessage("添加购物车成功");
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("添加购物车失败");
        }
    }

    @Override
    public ServerResponse deleteItem(Long userId, Long productId) {
        Cart cart = cartDAO.selectByUF(userId, productId);
        try {
            cartDAO.deleteById(cart.getId());
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse delAll(Long userId) {
        try {
            cartDAO.delAll(userId);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse check(Long userId, Long productId) {
        Cart cart = cartDAO.selectByUF(userId, productId);
        try {
            cartDAO.setCheck(cart.getId());
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse unCheck(Long userId, Long productId) {
        Cart cart = cartDAO.selectByUF(userId, productId);
        try {
            cartDAO.setUnCheck(cart.getId());
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse checkAll(Long userId) {
        List<Cart> Carts = cartDAO.getAllById(userId);
        try {
            for(Cart cart : Carts) {
                cartDAO.setCheck(cart.getId());
            }
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse unCheckAll(Long userId) {
        List<Cart> Carts = cartDAO.getAllById(userId);
        try {
            for(Cart cart : Carts) {
                cartDAO.setUnCheck(cart.getId());
            }
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse<Cart> selectByChecked() {
        return null;
    }
}
