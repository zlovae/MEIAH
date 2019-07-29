package com.meiya.got.service.impl;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.EventsVoDAO;
import com.meiya.got.dao.SeckillDAO;
import com.meiya.got.rabbitmq.SeckillMessage;
import com.meiya.got.service.ISeckillService;
import com.meiya.got.util.JedisUtil;
import com.meiya.got.util.RedisKeyUtil;
import com.meiya.got.vo.EventsVo;
import com.meiya.got.vo.SeckillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeckillServiceImpl implements ISeckillService {

    @Autowired
    private EventsVoDAO eventsVoDAO;

    @Autowired
    private SeckillDAO seckillDAO;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public ServerResponse getSeckillEvents() {
        try {
            List<EventsVo> eventsVoList = eventsVoDAO.activeEvent();
            return ServerResponse.createBySuccess(eventsVoList);
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse getSeckillGoods(Long eventId) {
        try {
            List<SeckillVo> seckillVoList = seckillDAO.getSeckillFoods(eventId);
            return ServerResponse.createBySuccess(seckillVoList);
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    @Override
    @Transactional
    public ServerResponse doSeckill(Long userId, Long foodId) {
        try {
            //1.redis预减库存
            String key = RedisKeyUtil.getSeckillStockKey(foodId);
            Long stock = jedisUtil.decr(key);
            if(stock < 0) {
                return ServerResponse.createByErrorMessage("秒杀已结束");
            }

            SeckillMessage seckillMessage = new SeckillMessage();
            seckillMessage.setUserId(userId);
            seckillMessage.setFoodId(foodId);
            //2.发送消息队列
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }
}
