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

    public User get(String name, String password) {
        return userDAO.getByNameAndPassword(name, password);
    }

    public void add(User user) {
        userDAO.save(user);

    }


}
