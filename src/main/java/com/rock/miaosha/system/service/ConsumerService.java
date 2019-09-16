package com.rock.miaosha.system.service;

import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.entity.Consumer;
import com.rock.miaosha.system.entity.Consumer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsumerService {
    int createConsumer(Consumer consumer);
    int updateConsumer(Consumer consumer);
    int deleteConsumer(Consumer consumer);
    Consumer findConsumerById(Consumer consumer);
    List<Consumer> findAllConsumers();

    List<Consumer> findConsumerByCondition(Wrapper<Consumer> consumerWrapper);

    Consumer login(Consumer consumer);

    String verifyConsumerAccount(String phone,String email);

}
