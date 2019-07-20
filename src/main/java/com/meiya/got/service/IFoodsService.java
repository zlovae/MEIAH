package com.meiya.got.service;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.po.Foods;
import org.hibernate.validator.constraints.EAN;

import java.util.List;

public interface IFoodsService {

    public ServerResponse<List<Foods>> selectByStore(Long storeId);

}
