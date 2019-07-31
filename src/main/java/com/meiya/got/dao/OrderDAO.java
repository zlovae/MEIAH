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
    String INSET_FIELDS = "id, store_id, user_id, shipping_id, payment, payment_type, qr_code, status, payment_time, send_time," +
            " end_time, close_time, create_time, update_time, note, address, comment_status";
    String SELECT_FIELDS = "store_id, user_id, shipping_id, payment, payment_type, qr_code, status, payment_time, send_time, end_time, close_time, create_time, update_time, note, address, comment_status";

    @Delete({"delete from " + TABLE_NAME + " where id = #{id}" })
    int deleteByPrimaryKey(Integer id);

    @Insert({"insert into my_order (", INSET_FIELDS,
            ") values (#{id},#{store_id},#{user_id},#{shipping_id}, #{payment}, #{payment_type}, #{qr_code}, #{status}, #{payment_time}, #{send_time}, " +
                    "#{end_time}, #{close_time}, #{create_time}, #{update_time}, #{note}, #{address}, #{comment_status})"})
    int insert(Order order);

    @Update({"" +
            "update my_order " +
            "set store_id=#{store_id}, user_id=#{user_id}, shipping_id=#{shipping_id}, payment=#{payment}, payment_type=#{payment_type}, " +
            "qr_code=#{qr_code}, status=#{status}, payment_time=#{payment_time}, send_time=#{send_time}, end_time=#{end_time}, " +
            "close_time=#{close_time}, create_time=#{create_time}, update_time=#{update_time}, note=#{note}, address=#{address}, comment_status=#{comment_status} "  +
            "where id=#{id}" +
            ""})
    int updateByKey(Order order);

    @Select({"select * from my_order where (id=#{order_id} and user_id=#{user_id} )"})
    Order selectByUserIdAndOrderNo(@Param("user_id") Long userId, @Param("order_id") Long orderId);


    @Select({"select * from my_order where id=#{id}"})
    Order selectByOrderNo(Long id);

    @Select({"select * from my_order where user_id=#{userId}"})
    List<Order> selectAllOrder(Long userId);

    @Update({"update my_order set comment_status=#{comment_status} where id=#{id}"})
    int updateCommentStatus(Order order);
}
