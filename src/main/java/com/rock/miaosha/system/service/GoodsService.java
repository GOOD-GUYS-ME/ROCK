package com.rock.miaosha.system.service;

import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.entity.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsService {
    int createGoods(Goods goods);
    int updateGoods(Goods goods);
    int deleteGoods(Goods goods);
    Goods findGoodsById(Goods goods);
    List<Goods> findAllGoods();

    int secKill();

    List<Goods> findGoodsByConditions(Wrapper<Goods> goods);
}
