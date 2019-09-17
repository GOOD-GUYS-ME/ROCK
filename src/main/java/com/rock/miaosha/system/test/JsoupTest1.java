package com.rock.miaosha.system.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rock.miaosha.common.utils.RedisUtils;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JsoupTest1 {

    public static void main(String[] args) {
//        String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/";
        Jedis jedis = new Jedis("192.168.43.97", 20001);
            CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        String s = jedis.get("上海市");
        System.out.println("s:"+s);
        System.out.println("JSON.toJSON(s):"+JSON.toJSON(s));
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put("id", 9);
        map.put("lid", 1);
        map.put("mid", 1);
        map.put("name", "上海市");
        map.put("provinceAddress", JSON.toJSON(s));
        list.add(map);
            System.out.println("list:" + list);
            Object o = JSON.toJSON(list);
            jedis.set("address",o.toString());
    }
}
