package com.meiya.got.service;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.vo.StoreVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStoreService {

    ServerResponse<List<StoreVo>> index();

}
