package com.rock.miaosha.system.dao;

import com.rock.miaosha.system.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsDaoCustom{
    @Query(value = "select id,amount,goods_name,price,size,state from goods where state=1 and goods_name like concat('%',?1,'%') limit ?2,?3",nativeQuery = true)
    List<Goods> findGoodsByConditions(String goods, int startPage, int size);
}
