package com.meiya.got.controller;


import com.meiya.got.common.Const;
import com.meiya.got.common.ServerResponse;
import com.meiya.got.po.Admin;
import com.meiya.got.service.IAdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private IAdminService iAdminService;

//    @PostMapping(value = "")
//    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        return "index";
//    }

    @PostMapping(value = "login")
    public ServerResponse<Admin> login(String phone, String password, HttpSession session){
        ServerResponse<Admin> response = iAdminService.login(phone,password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

}
