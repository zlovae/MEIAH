package com.meiya.got.dao;

import com.meiya.got.vo.StoreVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StoreVoDAO {

    String TABLE_NAME = "my_store";
    String SELECT_FIELDS = "id, name, description, photo_url, phone, cate_id, address, status";

    @Results(id = "StoreVo", value = {
            @Result(column = "id", property = "id", javaType = Long.class),
            @Result(column = "name", property = "name", javaType = String.class),
            @Result(column = "description", property = "description", javaType = String.class),
            @Result(column = "photo_url", property = "photo_url", javaType = String.class),
            @Result(column = "phone", property = "phone", javaType = String.class),
            @Result(column = "address", property = "address", javaType = String.class),
            @Result(column = "status", property = "status", javaType = Integer.class)
    })
    @Select({"select * from my_store"})
    List<StoreVo> listStores();


}
