package com.meiya.got.service.impl;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.FoodsDAO;
import com.meiya.got.po.Foods;
import com.meiya.got.service.IFoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodsServiceImpl implements IFoodsService {

    @Autowired
    private FoodsDAO foodsDAO;

    @Override
    public ServerResponse<List<Foods>> selectByStore(Long storeId) {
        List<Foods>  foodsList = foodsDAO.selectByStore(storeId);
        if(foodsList!=null) {
            return ServerResponse.createBySuccess(foodsList);
        } else {
            return ServerResponse.createBySuccessMessage("获取菜单失败！");
        }
    }
}
