package com.meiya.got.dao;
import com.meiya.got.po.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressDAO extends JpaRepository<Address, Long>{
    List<Address> findByUid(Long uid);
}
