package com.rock.miaosha.system.dao;

import com.rock.miaosha.system.entity.Goods;
import com.rock.miaosha.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsDao extends JpaRepository<Goods,Integer>,GoodsDaoCustom {
}
