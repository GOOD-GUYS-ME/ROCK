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
import java.util.concurrent.ConcurrentHashMap;

public class JsoupTest1 {

    private static StringBuffer stringBuffer=new StringBuffer();
//    private static Map map=new ConcurrentHashMap();
//    private static Map m=new ConcurrentHashMap();
    public static void main(String[]args) throws IOException {
        Jedis jedis = new Jedis("192.168.43.97", 20001);
//        Document document = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/").get();
////        System.out.println("document:"+document);
//        Elements select = document.select("tr.provincetr");
//        System.out.println("select:"+select);
//        Elements a = select.select("a");
//        System.out.println("a："+a);
//        String text = a.text();
//        String[] s = text.split(" ");
//
//
//        ArrayList<Object> l = new ArrayList<>();
//        for (int i = 0; i <s.length ; i++) {
//            ArrayList<Object> list = new ArrayList<>();
//            ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();
//            ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
//            hashMap.put("pid","0");
//            hashMap.put("provinceArr",s[i]);
//            list.add(hashMap);
//            map.put("id",(i+1));
//            map.put("addressInfo",list);
//            l.add(map);
//        }
//
////        jedis.flushDB();
//        Jackson2JsonRedisSerializer<Object> json = new Jackson2JsonRedisSerializer<>(Object.class);
//        byte[] serialize = json.serialize(l.toString());
//        String s1 = JSON.toJSONString(l);
//        System.out.println("s1:"+s1);
//        jedis.set("address",s1);
//
//
        String address = jedis.get("address");
        Object parse = JSON.parse(address);
        System.out.println("parse:"+parse);
//        System.out.println("objects:"+objects);
//        Document document = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/45.html").get();
//        Elements select = document.select("tr.citytr");
////        System.out.println("document:"+document);
//        System.out.println("select:"+select);
//        String a = select.select("a").text();
//        System.out.println("td:"+a);
//        String address = jedis.get("address");
////        String[] split = address.split(",");
////        for (String s : split) {
////            System.out.println("s:"+s);
////        }
//        System.out.println("s:"+address);
    }
}
