package com.meiya.got.dao;

import com.meiya.got.vo.EventsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EventsVoDAO {
    String TABLE_NAME = "my_events";

    @Select({"select * from my_events where end_time > now()"})
    List<EventsVo> activeEvent();

}
