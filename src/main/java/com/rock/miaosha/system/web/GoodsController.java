package com.rock.miaosha.system.web;

import com.rock.miaosha.common.wraper.Wrapper;
import com.rock.miaosha.system.entity.Goods;
import com.rock.miaosha.system.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
*@author rock
*@Date 2019/9/17 23:13
*@param
*@return
*
*/
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    public void secKill(){
        goodsService.secKill();
    }

    @RequestMapping("/getGoodsList")
    @ResponseBody
    public Wrapper getGoods(String goods){
        Wrapper<Goods> wrapper = new Wrapper<>();
        wrapper.setT(new Goods());
        List<Goods> goodsList=goodsService.findGoodsByConditions(wrapper);
        if (!goodsList.isEmpty()) {
            wrapper.setList(goodsList);
            wrapper.setCode(0);
            wrapper.setMsg("OK");
            return wrapper;
        }
        wrapper.setCode(400);
        wrapper.setMsg("获取商品列表失败");
        return wrapper;
    }
}
