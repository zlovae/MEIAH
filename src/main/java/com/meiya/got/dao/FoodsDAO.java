package com.meiya.got.dao;

import com.meiya.got.po.Foods;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface FoodsDAO {
    String TABLE_NAME = "my_foods";
    String INSET_FIELDS = "phone, password, salt";
    String SELECT_FIELDS = "id, sid, name, photo, photo_sub, cate_id, description, stock, price, status, create_time";


    @Select({"select * from my_foods where id = #{id}"})
    @Results(id = "FoodsMap", value = {
            @Result(column = "name", property = "name", javaType = String.class),
            @Result(column = "photo", property = "photo", javaType = String.class),
            @Result(column = "photo_sub", property = "photo_sub", javaType = String.class),
            @Result(column = "cate_id", property = "cate_id", javaType = Long.class),
            @Result(column = "description", property = "description", javaType = String.class),
            @Result(column = "stock", property = "stock", javaType = Integer.class),
            @Result(column = "price", property = "price", javaType = BigDecimal.class),
            @Result(column = "status", property = "status", javaType = Integer.class),
            //@Result(column = "create_time", property = "create_time", javaType = java.util.Date.class)
    })
    Foods selectById(@Param("id") Long id);

    @Select({"select * from my_foods where sid = #{storeId}"})
    List<Foods> selectByStore(@Param("storeId") Long storeId);


    @Update({"update my_foods set stock=#{stock} where id=#{id}"})
    int updateStock(@Param("id") Long id, @Param("stock") Integer stock);


}
