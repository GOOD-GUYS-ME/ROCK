package com.rock.miaosha.system.web;

import com.rock.miaosha.common.utils.MD5utils;
import com.rock.miaosha.common.utils.RedisUtils;
import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.entity.Consumer;
import com.rock.miaosha.system.entity.Sales;
import com.rock.miaosha.system.service.ConsumerService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
/**
*@author rock
*@Date 2019/9/17 23:13
*@param
*@return
*
*/
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;
    @Autowired
    RedisUtils redisUtils;
    @RequestMapping("/login")
    @ResponseBody
    public Wrapper<Sales> login(@RequestBody String form){
        System.out.println("form:"+form);
        Wrapper<Sales> wrapper = new Wrapper<>();
        String userAccount ;
        String password ;
        String captcha ;
        try {
            JSONObject jsonObject = new JSONObject(form);
            userAccount =(String) jsonObject.get("userAccount");
            password =(String) jsonObject.get("password");
            captcha =(String) jsonObject.get("captcha");

            String s = redisUtils.get("vCode");

            if (!captcha.equalsIgnoreCase(s)) {
                wrapper.setCode(400);
                wrapper.setMsg("验证码输入有误，请重新输入");
                return wrapper;
            }
            String salt=consumerService.verifyConsumerAccount(userAccount,userAccount);
                if (salt==null) {
                    wrapper.setCode(400);
                    wrapper.setMsg("您输入的账号有误，请重新输入");
                    return wrapper;
            }
            salt = "$%^"+salt+password+"%$#";
            Md5Hash md5 = new MD5utils(salt).getMd5();
            Consumer consumer = new Consumer();
            consumer.setPhone(userAccount);
            consumer.setEmail(userAccount);
            consumer.setPassword(md5.toString());
            Consumer login = consumerService.login(consumer);
            if (login == null) {
                wrapper.setCode(400);
                wrapper.setMsg("您输入的密码有误，请重新输入");
                return wrapper;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        wrapper.setCode(0);
        wrapper.setMsg("OK");
        wrapper.getT().setPassword(null);
        return wrapper;
    }

    @ResponseBody
    @RequestMapping("/insertConsumer")
    public Wrapper<Consumer> insertConsumer(@RequestBody String form){
        Wrapper<Consumer> salesWrapper = new Wrapper<>();
        String phone ;
        String password ;
        String email ;
        try {
            JSONObject jsonObject = new JSONObject(form);
            phone =(String) jsonObject.get("phone");
            email =(String) jsonObject.get("email");
            password =(String) jsonObject.get("password");
            Consumer consumer = new Consumer();
            consumer.setEmail(email);
            consumer.setPhone(phone);
            LocalDateTime now = LocalDateTime.now();
            String salt = "$%^"+now.toString()+password+"%$#";
            Md5Hash md5 = new MD5utils(salt).getMd5();
            consumer.setSalt(now.toString());
            consumer.setPassword(md5.toString());
            int i = consumerService.createConsumer(consumer);
            if (i ==0) {
                salesWrapper.setCode(400);
                salesWrapper.setMsg("注册失败，请稍后再试！");
                return salesWrapper;
            }
            salesWrapper.setCode(0);
            password=null;
            salesWrapper.setMsg("恭喜您！已经成功注册成为会员！");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return salesWrapper;
    }

}
