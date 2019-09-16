package com.rock.miaosha.system.test;

import com.alibaba.fastjson.JSON;
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

public class JsoupTest5 {

    private final static ConcurrentHashMap HASH_MAP = new ConcurrentHashMap();
    static {
        HASH_MAP.put(1, "provincetr");
        HASH_MAP.put(2, "citytr");
        HASH_MAP.put(3, "countytr");
        HASH_MAP.put(4, "towntr");
//        HASH_MAP.put(5,"villagetr");
    }

    public static void main(String[]args) throws IOException, ExecutionException, InterruptedException {
        String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/";
        int level=1;
        Jedis jedis = new Jedis("192.168.43.97", 20001);
        Document document = Jsoup.connect(URL).get();
        Elements select = document.select("tr." + HASH_MAP.get(level));

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService service=new ExecutorCompletionService(executorService);
        AtomicInteger id = new AtomicInteger(1);
        ArrayList<Object> list = new ArrayList<>();
        for (Element element : select) {

            Elements a = element.select("a");
            for (Element element1 : a) {
                ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                String href = element1.select("a").attr("abs:href");
                Future submit = service.submit(new GetCityAddress(href,level+1,id.get()));
                map.put("id",id.getAndIncrement());
                map.put("lid",1);
                map.put("mid",1);
                map.put("name",element1.select("a").last().text());
                map.put(HASH_MAP.get(level),submit.get());
                list.add(map);
            }

        }
        System.out.println("list:"+list);
        Object o = JSON.toJSON(list);
        jedis.set(HASH_MAP.get(level).toString(),o.toString());
        executorService.shutdown();
    }
    private static class GetCityAddress implements Callable<List>{
        private final String url;
        private final int level;
        private final int id;
        private AtomicInteger atomicInteger = new AtomicInteger(1);

        public GetCityAddress(String url, int level, int id) {
            this.url = url;
            this.level = level;
            this.id = id;
        }

        @Override
        public List call() throws Exception {
            Document document = Jsoup.connect(url).get();
            Elements select = document.select("tr." + HASH_MAP.get(level));
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            CompletionService service=new ExecutorCompletionService(executorService);
            AtomicInteger id1 = new AtomicInteger(1);


            ArrayList<Object> list = new ArrayList<>();
            for (Element element : select) {
                ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                String href = element.select("a").attr("abs:href");
                Future submit = service.submit(new GetCountryAddress(href,level+1,id1.get()));
                map.put("id",id1.getAndIncrement());
                map.put("lid",level);
                map.put("mid",id);
                map.put("name",element.select("a").last().text());
                map.put(HASH_MAP.get(level),submit.get());
                list.add(map);
            }
            executorService.shutdown();
            if (list != null&&!list.isEmpty()) {
                System.out.println("GetCityAddress:"+list);
                return list;
            }
            return null;
        }
    }
    private static class GetCountryAddress implements Callable<List>{
        private final String url;
        private final int level;
        private final int id;
        private AtomicInteger atomicInteger = new AtomicInteger(1);

        public GetCountryAddress(String url, int level, int id) {
            this.url = url;
            this.level = level;
            this.id = id;
        }

        @Override
        public List call() throws Exception {
            Document document = Jsoup.connect(url).get();
            Elements select = document.select("tr." + HASH_MAP.get(level));
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            CompletionService service=new ExecutorCompletionService(executorService);
            AtomicInteger id1 = new AtomicInteger(1);

            ArrayList<Object> list = new ArrayList<>();
            for (Element element : select) {
                ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                String href = element.select("a").attr("abs:href");
                Future submit = service.submit(new GetDowntownAddress(href,level+1,id1.get()));
                map.put("id",id1.getAndIncrement());
                map.put("lid",level);
                map.put("mid",id);
                map.put("name",element.select("a").last().text());
                map.put(HASH_MAP.get(level),submit.get());
                list.add(map);
            }
            System.out.println("GetCountryAddress:"+list);
            executorService.shutdown();
            if (list != null&&!list.isEmpty()) {
                return list;
            }
            return null;
        }
    }
    private static class GetDowntownAddress implements Callable<List>{
        private final String url;
        private final int level;
        private final int id;
        private AtomicInteger atomicInteger = new AtomicInteger(1);

        public GetDowntownAddress(String url, int level, int id) {
            this.url = url;
            this.level = level;
            this.id = id;
        }

        @Override
        public List call() throws Exception {
            Document document = Jsoup.connect(url).get();
            Elements select = document.select("tr." + HASH_MAP.get(level));
//            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//            CompletionService service=new ExecutorCompletionService(executorService);
            AtomicInteger id1 = new AtomicInteger(1);

            ArrayList<Object> list = new ArrayList<>();
            for (Element element : select) {
                ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
//                String href = element.attr("abs:href");
                map.put("id",id1.getAndIncrement());
                map.put("lid",level);
                map.put("mid",id);
                map.put("name",element.select("a").last().text());
                list.add(map);
            }
            System.out.println("GetDowntownAddress:"+list);
            if (list != null&&!list.isEmpty()) {
                return list;
            }
            return null;
        }
    }
}
