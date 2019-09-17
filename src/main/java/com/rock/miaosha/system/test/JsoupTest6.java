package com.rock.miaosha.system.test;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
/**
*@author rock
*@Date 2019/9/17 23:13
*@param
*@return
*
*/
public class JsoupTest6 {
    private final static ConcurrentHashMap HASH_MAP = new ConcurrentHashMap();

    static {
        HASH_MAP.put(1, "provincetr");
        HASH_MAP.put(2, "citytr");
        HASH_MAP.put(3, "countytr");
        HASH_MAP.put(4, "towntr");
//        HASH_MAP.put(5,"villagetr");
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/";
        int level = 1;
        Jedis jedis = new Jedis("192.168.43.97", 20001);
        Document document = Jsoup.connect(URL).timeout(50000).get();
        Elements select = document.select("tr." + HASH_MAP.get(level));

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService service=new ExecutorCompletionService(executorService);
        AtomicInteger id = new AtomicInteger(1);

        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        BlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        System.out.println("开始");

        for (Element element : select) {
            Elements a = element.select("a");
            for (Element element1 : a) {
                ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                String href = element1.select("a").attr("abs:href");
                executorService.submit(new GetCityAddress(queue,href,level+1,id.get()));
                map.put("id",id.getAndIncrement());
                map.put("lid",1);
                map.put("mid",1);
                map.put("name",element1.select("a").last().text());
                map.put(HASH_MAP.get(level),queue.take());
                list.add(map);
            }

        }
        Object o = JSON.toJSON(list);
        jedis.set(HASH_MAP.get(level).toString(),o.toString());
        System.out.println("list:"+list);
        System.out.println("结束");
        executorService.shutdown();
    }

    private static class GetCityAddress implements Runnable{
        private final BlockingQueue queue;
        private final String url;
        private final int level;
        private final int id;

        public GetCityAddress(BlockingQueue queue, String url, int level, int id) {
            this.queue = queue;
            this.url = url;
            this.level = level;
            this.id = id;
        }

        @Override
        public void run() {
            Document document = null;
            try {
                document = Jsoup.connect(url).get();
                Elements select = document.select("tr." + HASH_MAP.get(level));
                ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                CompletionService service=new ExecutorCompletionService(executorService);
                AtomicInteger id1 = new AtomicInteger(1);
                CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
                for (Element element : select) {
                    ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                    String href = element.select("a").attr("abs:href");
                    executorService.submit(new GetCountryAddress(queue,href,level+1,id1.get()));
                    map.put("id",id1.getAndIncrement());
                    map.put("lid",level);
                    map.put("mid",id);
                    map.put("name",element.select("a").last().text());
                    map.put(HASH_MAP.get(level),queue.take());
                    list.add(map);
                }
                executorService.shutdown();
                if (list != null&&!list.isEmpty()) {
                    System.out.println("GetCityAddress:"+list);
                    queue.put(list);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class GetCountryAddress implements Runnable{
        private final BlockingQueue queue;
        private final String url;
        private final int level;
        private final int id;

        public GetCountryAddress(BlockingQueue queue, String url, int level, int id) {
            this.queue = queue;
            this.url = url;
            this.level = level;
            this.id = id;
        }

        @Override
        public void run() {
            Document document = null;
            try {
                document = Jsoup.connect(url).get();
                Elements select = document.select("tr." + HASH_MAP.get(level));
                ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                CompletionService service=new ExecutorCompletionService(executorService);
                AtomicInteger id1 = new AtomicInteger(1);
                CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
                for (Element element : select) {
                    ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                    String href = element.select("a").attr("abs:href");
                    executorService.submit(new GetDowntownAddress(queue,href,level+1,id1.get()));
                    map.put("id",id1.getAndIncrement());
                    map.put("lid",level);
                    map.put("mid",id);
                    map.put("name",element.select("a").last().text());
                    map.put(HASH_MAP.get(level),queue.take());
                    list.add(map);
                }
                executorService.shutdown();
                if (list != null&&!list.isEmpty()) {
                    System.out.println("GetCountryAddress:"+list);
                    queue.put(list);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static class GetDowntownAddress implements Runnable{
        private final BlockingQueue queue;
        private final String url;
        private final int level;
        private final int id;

        public GetDowntownAddress(BlockingQueue queue, String url, int level, int id) {
            this.queue = queue;
            this.url = url;
            this.level = level;
            this.id = id;
        }

        @Override
        public void run() {
            Document document = null;
            try {
                document = Jsoup.connect(url).get();
                Elements select = document.select("tr." + HASH_MAP.get(level));
                ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                CompletionService service=new ExecutorCompletionService(executorService);
                AtomicInteger id1 = new AtomicInteger(1);
                CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
                for (Element element : select) {
                    ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                    String href = element.select("a").attr("abs:href");
//                    Future submit = service.submit(new JsoupTest5.GetCountryAddress(href,level+1,id1.get()));
                    map.put("id",id1.getAndIncrement());
                    map.put("lid",level);
                    map.put("mid",id);
                    map.put("name",element.select("a").last().text());
                    list.add(map);
                }
                executorService.shutdown();
                if (list != null&&!list.isEmpty()) {
                    System.out.println("GetDowntownAddress:"+list);
                    queue.put(list);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
