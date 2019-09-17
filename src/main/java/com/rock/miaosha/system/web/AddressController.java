package com.rock.miaosha.system.web;

import com.rock.miaosha.common.utils.RedisUtils;
import com.rock.miaosha.common.wraper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
*@author rock
*@Date 2019/9/17 23:13
*@param 
*@return 
*
*/
@RestController
public class AddressController {
@Autowired
    RedisUtils redisUtils;
    @RequestMapping("/setAddress")
    public void setAddress(){
        
    }
    @RequestMapping("/getProvinceAddress")
    @ResponseBody
    public Wrapper getProvinceAddress(){
        Wrapper<Object> wrapper = new Wrapper<>();
        String address = redisUtils.get("provinceAddress");
        System.out.println("address:"+address);
        if (StringUtils.isEmpty(address)) {
            wrapper.setCode(400);
            wrapper.setMsg("获取地址失败");
            return wrapper;
        }
        wrapper.setCode(0);
        wrapper.setMsg("OK");
        wrapper.setT(address);
        return wrapper;
    }
    @RequestMapping("/getCityAddress")
    @ResponseBody
    public Wrapper getCityAddress(String id){
        System.out.println("id:"+id);
        Wrapper<Object> wrapper = new Wrapper<>();
        String address = redisUtils.get("cityAddress");
        System.out.println("address:"+address);
        if (StringUtils.isEmpty(address)) {
            wrapper.setCode(400);
            wrapper.setMsg("获取地址失败");
            return wrapper;
        }
        wrapper.setCode(0);
        wrapper.setMsg("OK");
        wrapper.setT(address);
        return wrapper;
    }
}
