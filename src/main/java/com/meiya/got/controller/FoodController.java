package com.meiya.got.controller;

import com.meiya.got.po.Food;
import com.meiya.got.service.impl.FoodServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping
public class FoodController {
    @Autowired
    FoodServiceImpl foodService;

    @CrossOrigin
    @PostMapping(value = "/api/food")
    @ResponseBody
    public List<Food> foods(@RequestParam("sid") int sid){
        System.out.println(sid);
        return foodService.getByStoreid(sid);
    }
}
