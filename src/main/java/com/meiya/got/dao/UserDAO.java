package com.meiya.got.dao;

import com.meiya.got.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {

    User getByPhone(String name);

    User findByName(String name);

    User getByNameAndPassword(String name,String password);

    User getById(Long id);

}
