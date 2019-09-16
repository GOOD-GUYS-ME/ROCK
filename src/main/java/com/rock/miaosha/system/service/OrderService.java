package com.rock.miaosha.system.service;

import com.rock.miaosha.system.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    int createOrder(Order order);
    int updateOrder(Order order);
    int deleteOrder(Order order);
    Order findOrderById(Order order);
    List<Order> findAllOrders();
}
