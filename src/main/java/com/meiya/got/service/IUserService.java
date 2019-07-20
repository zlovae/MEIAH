package com.meiya.got.service;

import com.meiya.got.common.ServerResponse;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    ServerResponse login(String phone, String password);

    ServerResponse getId(String phone);

}
