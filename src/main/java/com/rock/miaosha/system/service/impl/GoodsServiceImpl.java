package com.rock.miaosha.system.service.impl;

import com.rock.miaosha.common.constant.Const;
import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.dao.GoodsDao;
import com.rock.miaosha.system.entity.Goods;
import com.rock.miaosha.system.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDao goodsDao;
    @Override
    @Transactional
    public int createGoods(Goods goods) {
        return 0;
    }

    @Override
    @Transactional
    public int updateGoods(Goods goods) {
        return 0;
    }

    @Override
    @Transactional
    public int deleteGoods(Goods goods) {
        return 0;
    }

    @Override
    public Goods findGoodsById(Goods goods) {
        return null;
    }

    @Override
    public List<Goods> findAllGoods() {
        return null;
    }

    @Override
    public int secKill() {
        return 0;
    }

    @Override
    public List<Goods> findGoodsByConditions(Wrapper<Goods> goods) {
        int currentPage = goods.getCurrentPage();
        int pageSize = goods.getPageSize();
        if (pageSize == 0) {
            pageSize=Const.DEFAULT_PAGE_SIZE;
        }
        try {
            if (StringUtils.isEmpty(goods.getT().getGoodsName())) {
                return goodsDao.findGoodsByConditions("",currentPage,pageSize);
            }else {
                return goodsDao.findGoodsByConditions(goods.getT().getGoodsName(),currentPage,pageSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
