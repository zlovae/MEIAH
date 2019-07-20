package com.meiya.got.dao;

import com.meiya.got.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDAO {

    @Select({"select  *  from my_user where phone = #{phone}"})
    User checkUser(String phone);

    @Select({"select id from my_user where phone =#{phone}"})
    Long getId(@Param("phone")String phone);

}
