package com.meiya.got.controller;

import com.meiya.got.po.Store;
import com.meiya.got.service.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StoresController {

    @Autowired
    private StoreServiceImpl storeService;

    @CrossOrigin
    @GetMapping(value = "/api/stores")
    @ResponseBody
    public List<Store> shops(){
        List<Store> all = storeService.findAll();
        System.out.println(all);
        return all;
    }

}
