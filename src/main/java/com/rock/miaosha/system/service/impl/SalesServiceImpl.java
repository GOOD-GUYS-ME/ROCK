package com.rock.miaosha.system.service.impl;

import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.dao.SalesDao;
import com.rock.miaosha.system.entity.Sales;
import com.rock.miaosha.system.service.SalesService;
import com.rock.miaosha.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesServiceImpl implements SalesService{

    @Autowired
    private SalesDao salesDao;
    @Override
    public int createSales(Sales sales) {
        return 0;
    }

    @Override
    public int updateSales(Sales sales) {
        return 0;
    }

    @Override
    public int deleteSales(Sales sales) {
        return 0;
    }

    @Override
    public Sales findSalesById(Sales sales) {
        return null;
    }

    @Override
    public List<Sales> findAllSales() {
        return null;
    }

    @Override
    public List<Sales> findSalesByCondition(Wrapper<Sales> salesWrapper) {
        return null;
    }

    @Override
    public Sales login(Sales sales) {
        return salesDao.login(sales.getPhone(),sales.getEmail(),sales.getPassword());
    }

    @Override
    public String verifySalesAccount(String phone,String email) {
       return salesDao.verifySalesAccount(phone, email);
    }
}
