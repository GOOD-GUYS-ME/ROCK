package com.rock.miaosha.system.service.impl;

import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.dao.ConsumerDao;
import com.rock.miaosha.system.entity.Consumer;
import com.rock.miaosha.system.service.ConsumerService;
import com.rock.miaosha.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerDao consumerDao;
    @Override
    public int createConsumer(Consumer consumer) {
        return 0;
    }

    @Override
    public int updateConsumer(Consumer consumer) {
        return 0;
    }

    @Override
    public int deleteConsumer(Consumer consumer) {
        return 0;
    }

    @Override
    public Consumer findConsumerById(Consumer consumer) {
        return null;
    }

    @Override
    public List<Consumer> findAllConsumers() {
        return null;
    }

    @Override
    public List<Consumer> findConsumerByCondition(Wrapper<Consumer> consumerWrapper) {
        return null;
    }

    @Override
    public Consumer login(Consumer consumer) {

        return consumerDao.login(consumer.getPhone(),consumer.getEmail(),consumer.getPassword());
    }

    @Override
    public String verifyConsumerAccount(String phone,String email) {
        return consumerDao.verifyConsumerAccount(phone, email);
    }
}
