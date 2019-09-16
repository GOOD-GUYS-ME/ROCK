package com.rock.miaosha.system.service;

import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.entity.Sales;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalesService {
    int createSales(Sales sales);
    int updateSales(Sales sales);
    int deleteSales(Sales sales);
    Sales findSalesById(Sales sales);
    List<Sales> findAllSales();

    List<Sales> findSalesByCondition(Wrapper<Sales> salesWrapper);

    Sales login(Sales sales);

    String verifySalesAccount(String phone,String email);
}
