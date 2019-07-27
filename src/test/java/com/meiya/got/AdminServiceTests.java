package com.meiya.got;

/**
 * Created by zlovae 2019/7/7
 * */

import com.meiya.got.dao.AdminDAO;
import com.meiya.got.po.Admin;
import com.meiya.got.po.MsgConnection;
import com.meiya.got.util.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTests {

//    @Autowired
//    private AdminDAO adminDAO;

//
//    @Autowired
//    private MsgConnection msgConnection;

//    @Test
//    public void test() {
//
//        int count = 1;
//        while (true) {
//            MsgConnection msgConnection = new MsgConnection((long) 2, 2, 2, 2, 2);
//            fanoutSender.send(msgConnection);
//            System.out.println("第" + count++ +"条消息"  + msgConnection);
//            try {
//                Thread.sleep(100000);
//            } catch (Exception e) {
//                System.out.println(e.toString());
//            }
//        }
//
//    }

//    @Test
//    public void run() {
//
//        while (true) {
//            MsgConnection msgConnection = new MsgConnection((long) 2, 2, 2);
//            fanoutSender.send(msgConnection);
//            Thread.sleep(60000);
//        }
//
//
//        //adminDAO.addAdmin("13606094177", MD5Util.MD5("1360609417712345"));
//
//        // adminDAO.addAdmin("1234", MD5Util.MD5("123412345"), "12345");
//        //Assert.assertEquals(1, adminDAO.checkUser("13888888888"));
//        //Admin admin = adminDAO.selectLogin(13888888888, )
//    }

}
