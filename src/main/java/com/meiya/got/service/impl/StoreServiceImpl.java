package com.meiya.got.service.impl;

import com.meiya.got.dao.StoreDAO;
import com.meiya.got.po.Store;
import com.meiya.got.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private StoreDAO storeDAO;

    public Store getByName(String store_name){
        return storeDAO.getByStorename(store_name);
    }

    public List<Store> findAll(){
        //
        return storeDAO.findAll();
    }
}
