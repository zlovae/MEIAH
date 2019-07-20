package com.meiya.got.controller;

import com.meiya.got.po.User;
import com.meiya.got.result.Result;
import com.meiya.got.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

    @Autowired
    UserServiceImpl userService;

    @CrossOrigin
    @PostMapping(value = "/api/register")
    @ResponseBody
    public Result register(@RequestBody User requestUser){
        try{
            System.out.println(requestUser);
            userService.add(requestUser);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(400);
        }
        return new Result(200);
    }
}
