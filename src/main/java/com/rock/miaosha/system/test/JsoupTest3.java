package com.rock.miaosha.system.test;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
/**
*@author rock
*@Date 2019/9/17 23:12
*@param
*@return
*
*/
public class JsoupTest3 {
    public static void main(String[]args) throws IOException {
        Jedis jedis = new Jedis("192.168.43.97", 20001);
        Document document = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/45.html").get();
        Elements select = document.select("tr.citytr");
        ArrayList<Object> list = new ArrayList<>();
        ArrayList<Object> l = new ArrayList<>();
        for (Element element : select) {
            String td = element.select("td").last().text();
//            System.out.println("td:"+td);
            list.add(td);
        }
        for (int i = 0; i <list.size() ; i++) {
            ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
            map.put("pid","1");
            map.put("fid","20");
            map.put("id",i+1);
            map.put("city",list.get(i));
            l.add(map);
        }
        Object o = JSON.toJSON(l);
        jedis.set("cityAddress",o.toString());
        System.out.println("list:"+l);
    }
}
