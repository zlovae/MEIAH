package com.meiya.got.dao;

import com.meiya.got.po.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StoresDAO {

    String TABLE_NAME = "my_store";
    String SELECT_FIELDS = "";

    @Select({"select id from my_store where category_id=(select category_id from my_store  where id=(SELECT store_id  FROM " +
            "(SELECT  store_id,count(store_id) as storecount from my_order " +
            "where user_id=#{userId} " +
            "group BY store_id " +
            "ORDER BY storecount desc " +
            "limit 0,1) as a))"})
    List<Store> pickOneStore(Long userId);

}
