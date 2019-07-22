package com.meiya.got.controller;

import com.meiya.got.common.Const;
import com.meiya.got.common.ServerResponse;
import com.meiya.got.po.Address;
import com.meiya.got.po.Store;
import com.meiya.got.po.User;
import com.meiya.got.result.Result;
import com.meiya.got.service.IStoreService;
import com.meiya.got.service.IUserService;
import com.meiya.got.service.impl.AddressServiceImpl;
import com.meiya.got.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AddressServiceImpl addressService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody User requestUser){
        //对html 标签进行转义，以防XSS攻击
        String username = requestUser.getName();
        username = HtmlUtils.htmlEscape(username);
        System.out.println(username);

        User user = userService.get(username, requestUser.getPassword());

        //System.out.println(user);

        if(user.getPhone().equals(requestUser.getPhone())){
            System.out.println(user.toString());
            HashMap<String, Object> result = new HashMap<String, Object>();
            List<Address> addresses = addressService.getByUid(user.getId());
            result.put("user", user);
            result.put("addresses", addresses);
            return result;

        }else {
            return null;
        }

    }
}
