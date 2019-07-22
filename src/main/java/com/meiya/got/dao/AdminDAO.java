package com.meiya.got.dao;

import com.meiya.got.po.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

//@Repository
@Mapper
public interface AdminDAO {
    String TABLE_NAME = "my_admin";
    String INSET_FIELDS = "phone, password";
    String SELECT_FIELDS = "id, name, phone, password,salt,role, create_time";

    @Insert({"insert into ", TABLE_NAME, " ( ", INSET_FIELDS, ") values ( #{phone}, #{password})"})
    int addAdmin(@Param("phone") String phone, @Param("password") String password);

    @Select({"select  *  from my_admin where phone = #{phone}"})
    Admin checkUser(String phone);

    @Select({"select * ", SELECT_FIELDS, " from ", TABLE_NAME, " where phone=#{phone} and password=#{password}"})
    Admin selectLogin(@Param("phone") String phone, @Param("password")String password);

}
