package com.meiya.got.dao;


import com.meiya.got.po.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zlovae on 2019/7/8
 * */
@Mapper
public interface OrderDAO {

    String TABLE_NAME = "my_order";
    String INSET_FIELDS = "id, store_id, user_id, shipping_id, payment, payment_type, status, payment_time, send_time," +
            " end_time, close_time, create_time, update_time";
    String SELECT_FIELDS = "id, order_no, user_id, shipping_id, payment, payment_type, postage, status, payment_time, send_time," +
            " end_time, close_time, create_time, update_time";

    @Delete({"delete from " + TABLE_NAME + " where id = #{id}" })
    int deleteByPrimaryKey(Integer id);

    @Insert({"insert into my_order (", INSET_FIELDS ,
            ") values (#{id},#{store_id},#{user_id},#{shipping_id}, #{payment}, #{payment_type}, #{status}, #{payment_time}, #{send_time}, " +
                    "#{end_time}, #{close_time}, #{create_time}, #{update_time})"})
    int insert(Order order);

    @Update({"update my_order set status=#{status} where id=#{id}"})
    int updateByKey(Order order);

    @Insert({})
    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    //int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    @Select({"select * from my_order where (id=#{order_id} and user_id=#{user_id} )"})
    Order selectByUserIdAndOrderNo(@Param("user_id") Long userId, @Param("order_id") Long orderId);


    @Select({"select * from my_order where id=#{id}"})
    Order selectByOrderNo(Long id);


    List<Order> selectByUserId(Integer userId);


    @Select({"select * from my_order where user_id=#{userId}"})
    List<Order> selectAllOrder(Long userId);
}
