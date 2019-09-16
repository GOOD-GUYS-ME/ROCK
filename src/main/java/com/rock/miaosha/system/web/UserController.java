package com.rock.miaosha.system.web;

import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.entity.User;
import com.rock.miaosha.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

//    @Autowired
//    private UserService userService;
//
////    @ResponseBody
//    public Wrapper<User> getUser(Wrapper<User> wrapper){
//        List<User> usersByCondition = userService.findUsersByCondition(wrapper);
//        Wrapper<User> userWrapper = new Wrapper<>();
//        userWrapper.setCode(0);
//        userWrapper.setMsg("ok");
//        userWrapper.setList(usersByCondition);
//        System.out.println("userWrapper:"+userWrapper);
//        return userWrapper;
//    }
}
