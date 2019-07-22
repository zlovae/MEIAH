package com.meiya.got.service.impl;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.FoodDAO;
import com.meiya.got.po.Food;
import com.meiya.got.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements IFoodService {
    @Autowired
    FoodDAO foodDAO;

    public List<Food> getByStoreid(int sid){
        return foodDAO.getBySid(sid);
    }

    public Food getByName(String name){
        return foodDAO.getByName(name);
    }

}
