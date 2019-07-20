//package com.meiya.got.dao;
//
//
//import com.meiya.got.po.Cart;
//import com.meiya.got.po.Foods;
//import org.apache.ibatis.annotations.*;
//
//import javax.validation.constraints.Max;
//import java.sql.Date;
//import java.util.List;
//
//@Mapper
//public interface CartDAO {
//    String TABLE_NAME = "my_cart";
//    String INSET_FIELDS = "user_id, product_id, quantity, checked, create_time, update_time";
//    String SELECT_FIELDS = "user_id, product_id, quantity, checked, create_time, update_time";
//
//    @Insert({"insert into my_cart ( user_id, product_id, quantity , create_time, update_time) values ( #{user_id}, #{product_id}, #{quantity}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP )"})
//    int insertItem(Cart cart);
//
//    @Update({"update my_cart set quantity = #{quantity} where id=#{id}"})
//    int addItem(Cart cart);
//
//
//    @Select({"Select * from my_cart where user_id=#{user_id} "})
//    @Results(id = "CartMap", value = {
//            @Result(column = "id", property = "id", javaType = Long.class),
//            @Result(column = "user_id", property = "user_id", javaType = Long.class),
//            @Result(column = "product_id", property = "product_id", javaType = Long.class),
//            @Result(column = "quantity", property = "quantity", javaType = Integer.class),
//            @Result(column = "checked", property = "checked", javaType = Integer.class),
//            @Result(column = "create_time", property = "create_time", javaType = java.sql.Date.class),
//            @Result(column = "update_time", property = "update_time", javaType = java.sql.Date.class)
//    })
//    Cart selectByUserId(@Param("user_id") Long user_id);
//
//    @Select({"SELECT * FROM my_cart WHERE user_id=#{user_id} AND product_id=#{product_id}"})
//    //@ResultMap("CartMap")
//    Cart selectByUF(@Param("user_id") Long user_id,@Param("product_id") Long product_id);
//
//    @Select({"select * from my_cart where user_id = #{user_id}"})
//    List<Cart> getAllById(@Param("user_id")Long user_id);
//
//    @Delete({"delete from my_cart where id = #{id} "})
//    int deleteById(@Param("id") Long id);
//
//    @Delete({"delete from my_cart where user_id = #{user_id} "})
//    int delAll(@Param("user_id") Long user_id);
//
//    @Update("update my_cart set checked = 1 where id=#{id}")
//    int setCheck(@Param("id")Long id);
//
//    @Update("update my_cart set checked = 0 where id=#{id}")
//    int setUnCheck(@Param("id")Long id);
//
//    @Update("update my_cart set checked = 1 where user_id=#{user_id}")
//    int setCheckAll(@Param("user_id")Long user_id);
//
//    @Update("update my_cart set checked = 0 where user_id=#{user_id}")
//    int setUnCheckAll(@Param("user_id")Long user_id);
//
//    @ResultMap("CartMap")
//    @Select({"select * from my_cart where (user_id=#{user_id} and checked=1)"})
//    List<Cart> selectByChecked(@Param("user_id")Long user_id);
//
//    @Delete({"delete from my_cart ( where user_id=#{userId} and checked=1 )"})
//    int delBought(Long userId);
//
//}
