package com.meiya.got.dao;


import com.meiya.got.po.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodDAO extends JpaRepository<Food, Integer> {
    List<Food> getBySid(int sid );
    Food getByName(String name);
}
