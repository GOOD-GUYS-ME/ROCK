package com.rock.miaosha;

import com.rock.miaosha.common.utils.MD5utils;
import com.rock.miaosha.common.utils.RedisUtils;
import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.entity.Goods;
import com.rock.miaosha.system.entity.Sales;
import com.rock.miaosha.system.service.SalesService;
import com.rock.miaosha.system.service.UserService;
import com.rock.miaosha.system.web.GoodsController;
import com.rock.miaosha.system.web.SalesController;
import com.rock.miaosha.system.web.UtilsController;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiaoshaApplicationTests {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    SalesController salesController;
    @Autowired
    SalesService salesService;

    @Autowired
    UtilsController  utilsController;

    @Autowired
    GoodsController goodsController;
//    @Test
//    public void contextLoads() {
//        User user=new User();
//        user.setUserName("老铁");
//        user.setAddress("东北");
//        user.setPhone("13000000000");
//        user.setState(1);
//        user.setId(2);
//        userService.updateUser(user);
//        User userById = userService.findUserById(user);
////        User save = userDao.save(user);
////        System.out.println("userById:"+userDao.findAll());
//        System.out.println("userById:"+userById);
//    }
//
//    @Autowired
//    UserController userController;
//
//    @Test
//    public void test1(){
//        Wrapper<User> user = new Wrapper<>();
//        User u = new User();
//        u.setUserName("铁");
//        user.setT(u);
////        user.setCurrentPage(1);
////        user.setPageSize(3);
//        System.out.println("t:"+user.getT().getUserName());
//        user=userController.getUser(user);
//        System.out.println("user:"+user);
//    }

    @Test
    public void test2() throws InstantiationException, IllegalAccessException {
//        String salt="201416684.50219247$&";
//        MD5utils md5utils = new MD5utils(salt);
//        System.out.println("get:"+md5utils.getMd5());
//        redisUtils.set("aa","dalafjlaf");
//        redisUtils.expire("aa",60);
//        System.out.println("aa:"+redisUtils.get("aa"));
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now:"+now.toString());
        String salt = "$%^"+"2019-08-20T22:43:51.093"+"11111111"+"%$#";
        System.out.println("salt:"+salt);
        Md5Hash md5 = new MD5utils(salt).getMd5();
        System.out.println("get:"+md5);
    }

    @Test
    public void test3(){
        Sales sales = new Sales();
        sales.setPhone("13800000000");
        sales.setPassword("11111111");
        System.out.println("1:"+sales.getPhone());
        System.out.println("2:"+sales.getPassword());
        String phone = sales.getPhone();
        String password = sales.getPassword();
//        JSONObject jsonObject = new JSONObject().getJSONObject(sales.toString());
        String s = redisUtils.get("vCode");
        String captcha="BRUDE";
        Wrapper<Sales> salesWrapper = new Wrapper<>();
        try {
        if (!captcha.equalsIgnoreCase(s)) {
            salesWrapper.setCode(400);
            salesWrapper.setMsg("验证码输入有误，请重新输入");
            System.out.println("程序执行完毕1");
            return;
        }
        String salt=salesService.verifySalesAccount(phone,null);
        if (salt==null) {
            salesWrapper.setCode(400);
            salesWrapper.setMsg("您输入的账号有误，请重新输入");
            System.out.println("程序执行完毕2");
            return;
        }
        salt = "$%^"+salt+password+"%$#";
        Md5Hash md5 = new MD5utils(salt).getMd5();
        Sales sales1=new Sales();
            sales1.setPhone(phone);
            sales1.setEmail(null);
            sales1.setPassword(md5.toString());
            System.out.println("getmd5:"+md5.toString());
        Sales login = salesService.login(sales1);
        if (login == null) {
            salesWrapper.setCode(400);
            salesWrapper.setMsg("您输入的密码有误，请重新输入");
            System.out.println("程序执行完毕3");
            return;
        }
    } catch (
    JSONException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    }
        salesWrapper.setCode(0);
        salesWrapper.setMsg("OK");
        System.out.println("程序执行完毕4");
        return;
    }

    @Test
    public void test4(){
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println("now:"+now);
//        Wrapper wrapper = utilsController.setActivityStartTime(now.toString());
//        System.out.println("wrapper:"+wrapper);
    }
    @Test
    public void test5(){
        Wrapper goods = goodsController.getGoods(null);
        System.out.println("goods:"+goods);
    }
}
