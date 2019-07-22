package com.meiya.got.service;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.po.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICartService {

    //购物车
    ServerResponse<ServerResponse<List<Cart>>> getCart(Long userId);

    //添加一个菜品到购物车
    ServerResponse<String> addItem(Long userId, Long foodId, Integer quantity);

    //删除一个条目
    ServerResponse<Cart> deleteItem(Long userId, Long foodId);

    //删除一个条目
    ServerResponse delAll(Long userId);

    //勾选某个条目
    ServerResponse<Cart> check(Long userId, Long foodId);

    //取消勾选某个条目
    ServerResponse<Cart> unCheck(Long userId, Long foodId);

    //全选
    ServerResponse<Cart> checkAll(Long userId);

    //全不选
    ServerResponse<Cart> unCheckAll(Long userId);

    //全不选
    ServerResponse<Cart> selectByChecked();

}
