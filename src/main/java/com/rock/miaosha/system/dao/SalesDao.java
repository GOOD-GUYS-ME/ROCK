package com.rock.miaosha.system.dao;

import com.rock.miaosha.system.entity.Goods;
import com.rock.miaosha.system.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesDao extends JpaRepository<Sales,Integer>,SalesDaoCustom {
}
