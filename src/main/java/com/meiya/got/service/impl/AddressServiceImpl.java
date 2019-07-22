package com.meiya.got.service.impl;

import com.meiya.got.dao.AddressDAO;
import com.meiya.got.po.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl {
    @Autowired
    AddressDAO addressDAO;

    public List<Address> getByUid(Long uid){
        return addressDAO.findByUid(uid);
    }
}
