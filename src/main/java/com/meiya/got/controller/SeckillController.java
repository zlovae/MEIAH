package com.meiya.got.controller;

import com.google.gson.Gson;
import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.EventsVoDAO;
import com.meiya.got.dao.FoodDAO;
import com.meiya.got.dao.SeckillDAO;
import com.meiya.got.po.Food;
import com.meiya.got.service.impl.SeckillServiceImpl;
import com.meiya.got.util.JedisUtil;
import com.meiya.got.util.RedisKeyUtil;
import com.meiya.got.vo.EventsVo;
import com.meiya.got.vo.SeckillFoodVo;
import com.meiya.got.vo.SeckillVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/api/seckill/")
public class SeckillController implements InitializingBean {

    //标记商品库存是否为空，减少对redis的访问 K-food_id V-库存
    Map<Long, Boolean> localStockMap = new HashMap<>();

    @Autowired
    private SeckillServiceImpl seckillService;

    @Autowired
    private SeckillDAO seckillDAO;

    @Autowired
    private EventsVoDAO eventsVoDAO;

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private FoodDAO foodDAO;

    /**
     * @function 获取秒杀活动列表
     * @return List<EventsVo>
     * @param
     * */
    @RequestMapping(value = "activities")
    @ResponseBody
    public ServerResponse SeckillActivities() {
        ServerResponse serverResponse = seckillService.getSeckillEvents();
        if (serverResponse.isSuccess()) {
            return serverResponse;
        }
        return ServerResponse.createByError();
    }

    /**
     * @function 获取对应活动的秒杀食品列表
     * @return List<Seckill>
     * @param eventId
     * */
    @RequestMapping(value = "foods")
    @ResponseBody
    public ServerResponse SeckillEvent(@RequestParam("eid")Long eventId) {
        ServerResponse serverResponse = seckillService.getSeckillGoods(eventId);
        if (serverResponse.isSuccess()) {
            return serverResponse;
        }
        return ServerResponse.createByError();
    }

    /**
     * @function 执行秒杀
     * @return List<Seckill>
     * @param userId
     * @param secFoodId 菜品id,对应food表中的id
     * */
    @RequestMapping(value = "dosec")
    @ResponseBody
    public ServerResponse doSeckill(@RequestParam("uid")Long userId, @RequestParam("skid")Long secFoodId) {
        //0.未登录跳转

        //1.检测商品是否还有库存，有则进行下一步，没有则返回结束消息
        boolean over = localStockMap.get(secFoodId);
        if(over)
            return ServerResponse.createByErrorMessage("秒杀结束");

        //2.检测用户是否已对对应商品进行了秒杀，有则转到付款页面，没有则进行下一步
        String key = RedisKeyUtil.getSeckillOrderKey();
        String value = userId + "" + secFoodId;
        boolean ordered = jedisUtil.sismember(key, value);
        if(ordered) {
            return ServerResponse.createByErrorMessage("您已秒杀过该商品");
        }

        //3.进行秒杀
        return seckillService.doSeckill(userId, secFoodId);
    }

    /**@fuction 初始化，将秒杀的商品加载到缓存
     * @return
     * @param
     * */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<EventsVo> eventsVoList = eventsVoDAO.activeEvent();
        String eventKey = RedisKeyUtil.getSeckillEventKey();
        for(EventsVo eventsVo : eventsVoList) {
            Long eventId = eventsVo.getId();
            jedisUtil.sadd(eventKey, eventId.toString());

            //将活动商品缓存
            List<SeckillVo> seckillVoList = seckillDAO.getSeckillFoods(eventId);
            for (SeckillVo seckillVo : seckillVoList) {

                Food food = foodDAO.getById(seckillVo.getId());

                SeckillFoodVo seckillFoodVo = new SeckillFoodVo();

                seckillFoodVo.setId(seckillVo.getId());
                seckillFoodVo.setName(food.getName());
                seckillFoodVo.setDescribe(food.getDescribe());
                seckillFoodVo.setPhoto_url(food.getphoto_url());
                seckillFoodVo.setGoods_id(food.getId());
                seckillFoodVo.setCounts(seckillVo.getCounts());
                seckillFoodVo.setSid(seckillVo.getSid());
                seckillFoodVo.setSeckill_price(seckillVo.getSeckill_price());
                seckillFoodVo.setStart_date(seckillVo.getStart_date());
                seckillFoodVo.setEvents_id(seckillVo.getEvents_id());
                seckillFoodVo.setEnd_date(seckillVo.getEnd_date());
                seckillFoodVo.setStatus(seckillVo.getStatus());

                //缓存库存
                jedisUtil.sadd(RedisKeyUtil.getSeckillStockKey(seckillVo.getId()), seckillVo.getCounts().toString());
                Gson gson = new Gson();
                //缓存Food对象
                jedisUtil.sadd(RedisKeyUtil.getSeckillGoodVoKey(), gson.toJson(seckillFoodVo));
                //初始化为未空
                localStockMap.put(seckillFoodVo.getGoods_id(), false);
            }
        }
    }
}