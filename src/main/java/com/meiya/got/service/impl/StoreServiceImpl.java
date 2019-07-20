package com.meiya.got.service.impl;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.StoreVoDAO;
import com.meiya.got.po.Store;
import com.meiya.got.service.IStoreService;
import com.meiya.got.vo.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private StoreVoDAO storeVoDAO;

    @Override
    public ServerResponse<List<StoreVo>> index() {
        List<StoreVo> storeList = storeVoDAO.listStores();
        return ServerResponse.createBySuccess(storeList);
    }
}
