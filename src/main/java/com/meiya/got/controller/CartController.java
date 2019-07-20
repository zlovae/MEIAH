//package com.meiya.got.controller;
//
//
//import com.meiya.got.common.Const;
//import com.meiya.got.common.ResponseCode;
//import com.meiya.got.common.ServerResponse;
//import com.meiya.got.po.Cart;
//import com.meiya.got.po.User;
//import com.meiya.got.service.ICartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Controller
//@RequestMapping("/api/cart")
//public class CartController {
//
//    //TODO redis缓存实现购物车功能
//
//    @Autowired
//    private ICartService iCartService;
//    /*
//    *
//    * */
//
//    @RequestMapping(value = "/add")
//    @ResponseBody
//    public ServerResponse<String> addItem(@RequestParam("storeid")Long storeid, @RequestParam("productid")Long productid, HttpSession session){
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if(user==null) {
//            return ServerResponse.createBySuccessMessage(ResponseCode.NEED_LOGIN.getDesc());
//        } else {
//            ServerResponse response = iCartService.addItem(user.getId(), storeid, productid);
//            if(response.isSuccess()){
//                return ServerResponse.createBySuccess();
//            }
//            return ServerResponse.createByError();
//        }
//    }
//
//    @RequestMapping(value = "/del")
//    @ResponseBody
//    public ServerResponse delItem(@RequestParam("storeid")Long storeid, @RequestParam(value = "productid") Long productid, HttpSession session){
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if(user==null) {
//            return ServerResponse.createBySuccessMessage(ResponseCode.NEED_LOGIN.getDesc());
//        } else {
//            ServerResponse response = iCartService.deleteItem(user.getId(), storeid, productid);
//            if(response.isSuccess()){
//                return ServerResponse.createBySuccess();
//            }
//            return ServerResponse.createByError();
//        }
//    }
//
//    @RequestMapping(value = "/delall")
//    @ResponseBody
//    public ServerResponse deleteAll(@RequestParam("storeid")Long storeid, HttpSession session){
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if(user==null) {
//            return ServerResponse.createBySuccessMessage(ResponseCode.NEED_LOGIN.getDesc());
//        } else {
//            ServerResponse response = iCartService.delAll(user.getId(), storeid);
//            if(response.isSuccess()){
//                return ServerResponse.createBySuccess();
//            }
//            return ServerResponse.createByError();
//        }
//    }
////
////    @RequestMapping(value = "/list")
////    @ResponseBody
////    public ServerResponse getCart(HttpSession session){
////        User user = (User) session.getAttribute(Const.CURRENT_USER);
////        if(user==null){
////            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
////        }
////        return iCartService.getCart(user.getId());
////    }
////
////
////    @RequestMapping(value = "/check")
////    @ResponseBody
////    public ServerResponse check(@RequestParam(value = "userid") Long userid, @RequestParam(value = "productid") Long productid, HttpSession session){
////        ServerResponse response = iCartService.check(userid, productid);
////        if(response.isSuccess()){
////            session.setAttribute(Const.CURRENT_USER,response.getData());
////        }
////        return response;
////    }
////
////    @RequestMapping(value = "/uncheck")
////    @ResponseBody
////    public ServerResponse unCheck(@RequestParam(value = "userid") Long userid, @RequestParam(value = "productid") Long productid, HttpSession session){
////        ServerResponse response = iCartService.unCheck(userid, productid);
////        if(response.isSuccess()){
////            session.setAttribute(Const.CURRENT_USER,response.getData());
////        }
////        return response;
////    }
////
////    @RequestMapping(value = "/checkall")
////    @ResponseBody
////    public ServerResponse checkAll(@RequestParam(value = "userid") Long userid, HttpSession session){
////        ServerResponse response = iCartService.checkAll(userid);
////        if(response.isSuccess()){
////            session.setAttribute(Const.CURRENT_USER,response.getData());
////        }
////        return response;
////    }
////
////    @RequestMapping(value = "/uncheckall")
////    @ResponseBody
////    public ServerResponse unCheckAll(@RequestParam(value = "userid") Long userid, HttpSession session){
////        ServerResponse response = iCartService.unCheckAll(userid);
////        if(response.isSuccess()){
////            session.setAttribute(Const.CURRENT_USER,response.getData());
////        }
////        return response;
////    }
//
//}
