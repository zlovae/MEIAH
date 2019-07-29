package com.meiya.got.service;

import com.meiya.got.common.ServerResponse;
import org.springframework.stereotype.Service;

@Service
public interface ISeckillService {

    ServerResponse getSeckillEvents();

    ServerResponse getSeckillGoods(Long eventId);

    ServerResponse doSeckill(Long userId, Long foodId);

}
