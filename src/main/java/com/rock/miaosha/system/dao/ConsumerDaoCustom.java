package com.rock.miaosha.system.dao;

import com.rock.miaosha.system.entity.Consumer;
import com.rock.miaosha.system.entity.Sales;
import com.rock.miaosha.system.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumerDaoCustom {
    @Query(value = "select id,address,age,create_time,email,last_update,password,phone,salt,sex,state,user_name,role_id from consumer where user_name like concat('%',?1,'%') limit ?2,?3",nativeQuery = true)
    public List<Consumer> findConsumerByConditions(String userName, int currentPage, int pageSize);

    @Query(value = "select salt from consumer where phone=?1 or email=?2",nativeQuery = true)
    public String verifyConsumerAccount(String phone,String email);

    @Query(value = "select * from consumer where (phone=?1 or email=?2) and password=?3",nativeQuery = true)
    public Consumer login(String phone, String email, String password);

}
