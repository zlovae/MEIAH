package com.meiya.got.service.impl;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.EventsVoDAO;
import com.meiya.got.dao.SeckillDAO;
import com.meiya.got.po.User;
import com.meiya.got.rabbitmq.SeckillMessage;
import com.meiya.got.rabbitmq.Sender;
import com.meiya.got.service.ISeckillService;
import com.meiya.got.util.JedisUtil;
import com.meiya.got.util.MD5Util;
import com.meiya.got.util.RedisKeyUtil;
import com.meiya.got.util.UUIDUtil;
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

    @Autowired
    private Sender sender;

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
    public ServerResponse doSeckill(Long userId, Long skId) {
        try {
            //1.redis预减库存
            String key = RedisKeyUtil.getSeckillStockKey(skId);
            Long stock = jedisUtil.decr(key);
            if(stock < 0) {
                return ServerResponse.createByErrorMessage("秒杀已结束");
            }

            System.out.println("开始秒杀。。。");
            SeckillMessage seckillMessage = new SeckillMessage();
            seckillMessage.setUserId(userId);
            seckillMessage.setFoodId(skId);
            System.out.println(seckillMessage);
            //2.发送消息队列
            sender.sendSeckillOrder(seckillMessage);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
    }

    /**
     * 创建秒杀地址, 并将其存储在redis中
     *
     * @param userId
     * @param seckFoodId
     * @return
     */
    public ServerResponse createSeckillPath(Long userId, long seckFoodId) {
        try {
            // 随机生成秒杀地址
            String path = MD5Util.MD5(UUIDUtil.uuid() + "123456");
            // 将随机生成的秒杀地址存储在redis中（保证不同的用户和不同商品的秒杀地址是不一样的）
            jedisUtil.hset(RedisKeyUtil.getSeckillPathKey(), RedisKeyUtil.getSeckillPathField(userId, seckFoodId), path);
            return ServerResponse.createBySuccess(path);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.toString());
        }
    }

}
