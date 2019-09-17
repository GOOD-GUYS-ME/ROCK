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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JsoupTest4 {
    private final static int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private final static AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    private final static ConcurrentHashMap HASH_MAP = new ConcurrentHashMap();
    private final static String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/";

    static {
        HASH_MAP.put(1, "provincetr");
        HASH_MAP.put(2, "citytr");
        HASH_MAP.put(3, "countytr");
        HASH_MAP.put(4, "towntr");
//        HASH_MAP.put(5,"villagetr");
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(CPU_COUNT);
        CompletionService service = new ExecutorCompletionService(executorService);
        AtomicInteger atomicInteger = new AtomicInteger(1);
        List<Object> list = new CopyOnWriteArrayList<>();
        Jedis jedis = new Jedis("192.168.43.97", 20001);
//        System.out.println("jedis:" + jedis);
        Document document = Jsoup.connect(URL).timeout(50000).get();
        int level = 1;
        Elements select = document.select("tr." + HASH_MAP.get(level));
        Elements a = select.select("a");
        for (Element element : a) {
            ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
            String attr = element.attr("abs:href");

             Future<?> submit = service.submit(new GetNextAddress(attr, level + 1, atomicInteger.get()));

            map.put("id", atomicInteger.getAndIncrement());
            map.put("lid", level);
            map.put("fid", 1);
            map.put("name", element.select("a").last().text());
            map.put(HASH_MAP.get(level), submit.get());
            list.add(map);
            Object o = JSON.toJSON(list);
            jedis.set(HASH_MAP.get(level).toString(), o.toString());
        }
        System.out.println("provincetrList:" + list);

    }


    private static class GetNextAddress implements Callable<List> {
        private final String url;
        private final int level;
        private final int id;
        private AtomicInteger atomicInteger = new AtomicInteger(1);

        public GetNextAddress(String url, int level, int id) {
            this.url = url;
            this.level = level;
            this.id = id;
        }

        @Override
        public List call() throws Exception {
//            ExecutorService executorService = Executors.newFixedThreadPool(CPU_COUNT);
//            CompletionService service = new ExecutorCompletionService(executorService);

            try {
                Document document = Jsoup.connect(url).timeout(50000).get();
                Elements select = document.select("tr." + HASH_MAP.get(level));
                AtomicInteger atomicInteger = new AtomicInteger(1);
                ArrayList<Object> list = new ArrayList<>();
                for (Element element : select) {
                    HashMap<Object, Object> map = new HashMap<>();
                    String href= element.select("a").attr("abs:href");
                    List call = new GetNextAddress(href, level + 1, atomicInteger.get()).call();

                    map.put("id", atomicInteger.getAndIncrement());
                    map.put("lid", level);
                    map.put("fid", id);
                    map.put("name", element.select("a").last().text());
                    if (level < 4) {
                        map.put(HASH_MAP.get(level), call);
                    }
                    list.add(map);
                }
                if (list != null &&!list.isEmpty()) {
                    System.out.println("list:" + list);
                    return list;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        }
}
