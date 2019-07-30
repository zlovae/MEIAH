package com.meiya.got.dao;

import com.meiya.got.vo.SeckillVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface SeckillDAO {

    String TABLE_NAME = "my_seckill";

    @Select({"select * from my_seckill where events_id=#{events_id}"})
    List<SeckillVo> getSeckillFoods(@Param("events_id") Long events_id);

    @Update({"update my_seckill set counts=counts-1 where id=#{skId} and counts>0"})
    int reduceStock(Long skId);

    @Select({"select * from my_seckill where id=#{id}"})
    SeckillVo getByFoodId(@Param("id")Long id);
}
