package com.meiya.got.dao;

import com.meiya.got.po.Order;
import com.meiya.got.po.OrderItem;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderItemDAO {
    String TABEL_NAME = "my_order_item";
    String INSERT_FIELDS = "name, order_id, food_id, photo, price, quantity, discount_id, total_price";
    String SELECT_FIELDS = "name, order_id, food_id, photo, price, quantity, discount_id, total_price";

    @Results(id = "OrderItemMap", value = {
            //@Result(column = "id", property = "id", javaType = Long.class),
            @Result(column = "food_id", property = "food_id", javaType = Long.class),
            @Result(column = "name", property = "name", javaType = String.class),
            //@Result(column = "order_id", property = "order_id", javaType = Long.class),
            //@Result(column = "photo", property = "photo", javaType = String.class),
            @Result(column = "price", property = "price", javaType = BigDecimal.class),
            @Result(column = "quantity", property = "quantity", javaType = Integer.class),
            //@Result(column = "discount_id", property = "discount_id", javaType = Long.class),
            //@Result(column = "total_price", property = "total_price", javaType = BigDecimal.class)
    })
    @Select({"select * from my_order_item where order_id=#{order_id}"})
    List<OrderItem> selectByOrderId(@Param("order_id") Long order_id);

    @Insert({
            "<script>",
            "insert into my_order_item ( ", INSERT_FIELDS, " ) values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.name}, #{item.order_id}, #{item.food_id}, #{item.photo}, #{item.price}, #{item.quantity}, #{item.discount_id}, #{item.total_price})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param(value = "list") List<OrderItem> orderItemList);

    @Select({"select * from my_order_item where order_id=#{order_id}"})
    List<OrderItem> selectItemByOrderId(Long order_id);

    @Insert({"insert into my_order_item (", INSERT_FIELDS, " ) values (#{name}, #{order_id}, #{food_id}, #{photo}, #{price}, #{quantity}, #{discount_id}, #{total_price} )"})
    int insert(OrderItem orderItem);

}
