package com.meiya.got.dao;


import com.meiya.got.po.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreDAO extends JpaRepository<Store, Integer> {
    Store findByStorename(String storename);

    Store getByStorename(String storename);

}
