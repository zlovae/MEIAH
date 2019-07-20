package com.meiya.got.controller;

import com.meiya.got.common.Const;
import com.meiya.got.common.ServerResponse;
import com.meiya.got.po.Admin;
import com.meiya.got.po.Cart;
import com.meiya.got.po.Foods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping
public class FoodsController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     * 显示菜品详情
     * */
    @RequestMapping(value = {"/foods/{food_id}"}, method = {RequestMethod.POST})
    public void showFoodDetails(@PathVariable("food_id")Long food_id, HttpSession session) {

    }
}
