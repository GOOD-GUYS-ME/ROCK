package com.rock.miaosha.system.dao;

import com.rock.miaosha.system.entity.Sales;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesDaoCustom {
    @Query(value = "select id,address,age,create_time,email,last_update,password,phone,salt,sex,state,user_name,role_id from sales where user_name like concat('%',?1,'%') limit ?2,?3",nativeQuery = true)
    public List<Sales> findSalesByConditions(String userName, int currentPage, int pageSize);


    @Query(value = "select salt from sales where phone=?1 or email=?2",nativeQuery = true)
    public String verifySalesAccount(String phone,String email);

    @Query(value = "select * from sales where (phone=?1 or email=?2) and password=?3",nativeQuery = true)
    public Sales login(String phone,String email,String password);
}
