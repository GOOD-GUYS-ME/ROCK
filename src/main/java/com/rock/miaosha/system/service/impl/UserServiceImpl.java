package com.rock.miaosha.system.service.impl;

import com.rock.miaosha.common.constant.Const;
import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.service.UserService;
import com.rock.miaosha.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

//@Component
public class UserServiceImpl implements UserService {

//    @Autowired
//    private UserDao userDao;
//    @Override
//    @Transactional
//    public int createUser(User user) {
//        User u = userDao.save(user);
//        if (u!=null) {
//            return 1;
//        }
//        return 0;
//    }
//
//    @Override
//    @Transactional
//    public int updateUser(User user) {
//        User u = userDao.save(user);
//        if (!StringUtils.isEmpty(u)||u!=null) {
//            return 1;
//        }
//        return 0;
//    }
//
//    @Override
//    @Transactional
//    public int deleteUser(User user) {
//        userDao.delete(user);
//        User userById = findUserById(user);
//        if (userById != null) {
//            return 1;
//        }
//        return 0;
//    }
//
//    @Override
//    public User findUserById(User user) {
////        Optional<User> byId = userDao.findById(user.getId());
////        return byId.get();
//        return null;
//    }
//
//    @Override
//    public List<User> findAllUsers() {
//        Iterable<User> all = userDao.findAll();
//        List<User>list=new ArrayList<>();
//        for (User user : all) {
//            list.add(user);
//        }
//        return list;
//    }
//
//    @Override
//    public List<User> findUsersByCondition(Wrapper<User> userWrapper) {
//        int currentPage = userWrapper.getCurrentPage()-1;
//        int pageSize = userWrapper.getPageSize();
//        if (pageSize == 0) {
//            pageSize=Const.DEFAULT_PAGE_SIZE;
//        }
//        if (currentPage == -1) {
//            currentPage=Const.DEFAULT_CURRENT_PAGE-1;
//        }
//        String userName = userWrapper.getT().getUserName();
//        List<User> userByConditions = userDao.findUserByConditions(userName, currentPage, pageSize);
//        return userByConditions;
//    }
}
