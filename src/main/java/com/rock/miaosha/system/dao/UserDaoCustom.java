package com.rock.miaosha.system.dao;

import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDaoCustom {
    @Query(value = "select id,address,age,email,password,phone,salt,sex,state,user_name from user where user_name like concat('%',?1,'%') limit ?2,?3",nativeQuery = true)
    public List<User> findUserByConditions(String userName,int currentPage,int pageSize);
}
