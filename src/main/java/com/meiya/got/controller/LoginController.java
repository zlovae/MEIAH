package com.meiya.got.controller;

import com.meiya.got.common.Const;
import com.meiya.got.common.ServerResponse;
import com.meiya.got.po.Store;
import com.meiya.got.po.User;
import com.meiya.got.service.IStoreService;
import com.meiya.got.service.IUserService;
import com.meiya.got.vo.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private IStoreService iStoreService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = {"/", "/index"})
    @ResponseBody
    public ServerResponse<List<StoreVo>> index() {
        ServerResponse<List<StoreVo>> response = iStoreService.index();
        return response;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ServerResponse login(String phone, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(phone, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }
}
