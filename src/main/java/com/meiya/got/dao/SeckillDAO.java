package com.meiya.got.dao;

import com.meiya.got.vo.SeckillVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SeckillDAO {

    String TABLE_NAME = "my_seckill";

    @Select({"select * from my_seckill where events_id=#{events_id}"})
    List<SeckillVo> getSeckillFoods(Long events_id);

    @Update({"update my_seckill set counts=counts-1 where foodId=#{foodId} and counts>0"})
    int reduceStock(Long foodId);

}
