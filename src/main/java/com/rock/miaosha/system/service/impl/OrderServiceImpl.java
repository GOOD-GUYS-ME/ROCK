package com.rock.miaosha.system.service.impl;

import com.rock.miaosha.system.entity.Order;
import com.rock.miaosha.system.service.OrderService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
@Component
public class OrderServiceImpl implements OrderService {
    @Override
    @Transactional
    public int createOrder(Order order) {
        return 0;
    }

    @Override
    @Transactional
    public int updateOrder(Order order) {
        return 0;
    }

    @Override
    @Transactional
    public int deleteOrder(Order order) {
        return 0;
    }

    @Override
    public Order findOrderById(Order order) {
        return null;
    }

    @Override
    public List<Order> findAllOrders() {
        return null;
    }
}
