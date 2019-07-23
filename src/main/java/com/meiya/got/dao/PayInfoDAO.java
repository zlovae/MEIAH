package com.meiya.got.dao;

import com.meiya.got.po.PayInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface PayInfoDAO {
    String TABLE_NAME = "my_pay_info";
    String INSERT_FIELDS = " ( user_id, store_id, order_id, shipping_id, shipping_price, payment, platform, status, serial_number, creat_time, update_time ) ";


    @Insert({"insert into my_pay_info ", INSERT_FIELDS, " values (#{user_id}, #{order_id}, #{platform}, #{status}, #{serial_number})"})
    int insert(PayInfo payInfo);
}
