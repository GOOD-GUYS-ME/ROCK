package com.rock.miaosha.system.dao;

import com.rock.miaosha.system.entity.Consumer;
import com.rock.miaosha.system.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerDao extends JpaRepository<Consumer,Integer>,ConsumerDaoCustom {
}
