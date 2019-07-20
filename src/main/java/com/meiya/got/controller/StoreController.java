package com.meiya.got.controller;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.dao.FoodsDAO;
import com.meiya.got.po.Foods;
import com.meiya.got.service.IFoodsService;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StoreController {

    @Autowired
    private IFoodsService iFoodsService;

    @RequestMapping(value = {"/store"}, method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Foods>> foodList(@RequestParam("store_id")Long store_id) {
        ServerResponse<List<Foods>> response = iFoodsService.selectByStore(store_id);
        return response;
    }

}
