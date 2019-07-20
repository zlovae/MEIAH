package com.meiya.got.service;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.po.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICartService {

//    //添加一个菜品到购物车
//    ServerResponse addItem(Long userId, Long storeId, Long foodId);
//
//    //删除一个条目
//    ServerResponse<Cart> deleteItem(Long userId, Long storeId, Long foodId);

    //删除所有条目
    ServerResponse delAll(String phone, Long storeId);

//    //勾选某个条目
//    ServerResponse<Cart> check(Long userId, Long foodId);
//
//    //取消勾选某个条目
//    ServerResponse<Cart> unCheck(Long userId, Long foodId);
//
//    //全选
//    ServerResponse<Cart> checkAll(Long userId);
//
//    //全不选
//    ServerResponse<Cart> unCheckAll(Long userId);

}
