package com.rock.miaosha.system.dao;

import com.rock.miaosha.system.entity.Order;
import com.rock.miaosha.system.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface OrderDao extends CrudRepository<Order,Integer> {
}
