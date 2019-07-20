package com.meiya.got.service.impl;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.UserDAO;
import com.meiya.got.po.User;
import com.meiya.got.service.IUserService;
import com.meiya.got.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public ServerResponse login(String phone, String password) {
        User user = userDAO.checkUser(phone);
        if(user == null ){
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String md5Password = MD5Util.MD5(password+"12345");
        if(!user.getPassword().equals(md5Password)){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        //admin.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);
    }

    @Override
    public ServerResponse getId(String phone) {
        return null;
    }
}
