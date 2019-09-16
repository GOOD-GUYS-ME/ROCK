package com.rock.miaosha.system.test;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class JsoupTest2 {
    public static void main(String[]args) throws Exception{
        Jedis jedis = new Jedis("192.168.43.97", 20001);
        Document document = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/").get();
        Elements select = document.select("tr.provincetr");
//        System.out.println("select:"+select);
        String a = select.select("a").text();
        System.out.println("a:"+a);
        String[] s = a.split(" ");
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i <s.length ; i++) {
            ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
            map.put("pid","0");
            map.put("id",i+1);
            map.put("provincetr",s[i]);
            list.add(map);
        }
        Object o = JSON.toJSON(list);
        jedis.flushDB();
        jedis.set("provinceAddress",o.toString());
        System.out.println("o:"+o);
    }
}
