package com.rock.miaosha.system.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.ListUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JsoupTest4 {
    private final static int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private final static AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    private final static ConcurrentHashMap HASH_MAP = new ConcurrentHashMap();
    private final static String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/";

    static {
        HASH_MAP.put(1, "provincetr");
        HASH_MAP.put(2, "citytr");
        HASH_MAP.put(3, "countytr");
        HASH_MAP.put(4, "towntr");
//        HASH_MAP.put(5,"villagetr");
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Jedis jedis = new Jedis("192.168.43.97", 20001);
        System.out.println("jedis:" + jedis);
        Document document = Jsoup.connect(URL).get();
        int level = 1;
        Elements select = document.select("tr." + HASH_MAP.get(level));
//        System.out.println("HASH_MAP.get(level):"+HASH_MAP.get(level));
        Elements a = select.select("a");
//        System.out.println("a:"+a);
        AtomicInteger atomicInteger = new AtomicInteger(1);
        ArrayList<Object> list = new ArrayList<>();
        for (Element element : a) {
            ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
            String attr = element.attr("abs:href");
            String text = element.text();
            int id = atomicInteger.getAndIncrement();

//            System.out.println("attr:"+attr);
//            System.out.println("text:"+text);
//            System.out.println("element.id():"+id);
            ExecutorService executorService = Executors.newFixedThreadPool(CPU_COUNT);
            CompletionService service = new ExecutorCompletionService(executorService);
            Future<?> submit = service.submit(new getNextAddress(attr, level + 1, id));

            map.put("id", id);
            map.put("mid", level);
            map.put("fid", 1);
            map.put("name", element.select("a").last().text());
            map.put(HASH_MAP.get(level), submit.get());
            list.add(map);
            Object o = JSON.toJSON(list);
            String set = jedis.set(HASH_MAP.get(level).toString(), o.toString());
//            Object o = submit.get();
        }
        System.out.println("provincetrList:" + list);

    }


    private static class getNextAddress implements Callable<List> {
        private final String url;
        private final int level;
        private final int id;
        private AtomicInteger atomicInteger = new AtomicInteger(1);

        public getNextAddress(String url, int level, int id) {
            this.url = url;
            this.level = level;
            this.id = id;
        }

        @Override
        public List call() throws Exception {
            return getAddress(url, level, id);
        }

        public List getAddress(String href, int level, int id) {
            try {
                Document document = Jsoup.connect(href).get();
                Elements select = document.select("tr." + HASH_MAP.get(level));
                AtomicInteger atomicInteger = new AtomicInteger(1);
                ArrayList<Object> list = new ArrayList<>();
                for (Element element : select) {
                    ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                    int newId = atomicInteger.getAndIncrement();
                    String href1 = element.select("a").attr("abs:href");

                    map.put("id", newId);
                    map.put("mid", level);
                    map.put("fid", id);
                    map.put("name", element.select("a").last().text());

//                    List address = getAddress(href, 3, newId);
//                    if (address != null && !address.isEmpty()) {
//                        System.out.println("level:" + level);
//
//                    }

                    Document document1 = Jsoup.connect(href1).get();
                    Elements select1 = document1.select("tr." + HASH_MAP.get(level + 1));
                    ArrayList<Object> list1 = new ArrayList<>();
                    for (Element element1 : select1) {
                        ConcurrentHashMap<Object, Object> map1 = new ConcurrentHashMap<>();
                        int newId1 = atomicInteger.getAndIncrement();
                        map1.put("id", newId1);
                        map1.put("mid", level+1);
                        map1.put("fid", id);
                        map1.put("name", element1.select("a").last().text());
                        list1.add(map1);
                        System.out.println("element1:"+element1);
                    }
                    System.out.println("list1:" + list1);
                    map.put(HASH_MAP.get(level), list1);
                    list.add(map);
                }
                if (list == null || list.isEmpty()) {
                    return null;
                }
                System.out.println("list:" + list);
                return list;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
