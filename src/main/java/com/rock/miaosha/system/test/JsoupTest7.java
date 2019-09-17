package com.rock.miaosha.system.test;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JsoupTest7 {

    private final static ConcurrentHashMap HASH_MAP = new ConcurrentHashMap();

    static {
        HASH_MAP.put(1, "provincetr");
        HASH_MAP.put(2, "citytr");
        HASH_MAP.put(3, "countytr");
        HASH_MAP.put(4, "towntr");
//        HASH_MAP.put(5,"villagetr");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/44.html";
        int level = 1;
        Document document = null;
        try {
            document = Jsoup.connect(URL).timeout(500000).get();
            Elements select = document.select("tr." + HASH_MAP.get(level));

            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            CompletionService service = new ExecutorCompletionService(executorService);
            AtomicInteger id = new AtomicInteger(1);
            Future submit = service.submit(new GetCityList(URL, level + 1, id.get(),"广东省"));
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static class GetCityList implements Callable<List> {
        private final String url;
        private final String name;
        private final int level;
        private final int id;

        public GetCityList(String url, int level, int id,String name) {
            this.url = url;
            this.level = level;
            this.id = id;
            this.name = name;
        }

        @Override
        public List call() throws Exception {
            Document document = null;
            try {
                Jedis jedis = new Jedis("192.168.43.97", 20001);
                document = Jsoup.connect(url).timeout(500000).get();
                Elements select = document.select("tr." + HASH_MAP.get(level));
                ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                CompletionService service = new ExecutorCompletionService(executorService);
                AtomicInteger id1 = new AtomicInteger(1);

                CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
                for (Element element : select) {
                    ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                    String href = element.select("a").attr("abs:href");
                    if (href != null && !href.isEmpty()) {
                        Future submit = service.submit(new GetCountryList(href, level + 1, id1.get()));
                        if (submit != null) {
                            map.put("id", id1.getAndIncrement());
                            map.put("lid", level);
                            map.put("mid", id);
                            map.put("name", element.select("a").last().text());
                            map.put(HASH_MAP.get(level), submit.get());
                            list.add(map);
                        }
                    }
                }
                executorService.shutdown();
                if (list != null && !list.isEmpty()) {
                    System.out.println("GetCityList:" + list);
                    Object o = JSON.toJSON(list);
                    jedis.set(name,o.toString());
                    return list;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private static class GetCountryList implements Callable<List> {
        private final String url;
        private final int level;
        private final int id;

        public GetCountryList(String url, int level, int id) {
            this.url = url;
            this.level = level;
            this.id = id;
        }

        @Override
        public List call() throws Exception {
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            CompletionService service = new ExecutorCompletionService(executorService);
            AtomicInteger id1 = new AtomicInteger(1);

            CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
            Document document = null;
            try {
                document = Jsoup.connect(url).timeout(500000).get();
                Elements select = document.select("tr." + HASH_MAP.get(level));
                if (select == null||select.isEmpty()) {
                    Elements select1 = document.select("tr." + HASH_MAP.get(level + 1));
                    if (select1 != null&&!select1.isEmpty()) {
                        for (Element element : select1) {
                            ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                            map.put("id", id1.getAndIncrement());
                            map.put("lid", level);
                            map.put("mid", id);
                            map.put("name", element.select("a").last().text());
                            list.add(map);
                        }
                    }
                    System.out.println("select1:"+select1);
                    return list;
                }

                for (Element element : select) {
                    ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                    String href = element.select("a").attr("abs:href");
                    if (href != null && !href.isEmpty()) {
                        Future submit = service.submit(new GetDowntownList(href, level + 1, id1.get()));
                        if (submit != null) {
                            map.put("id", id1.getAndIncrement());
                            map.put("lid", level);
                            map.put("mid", id);
                            map.put("name", element.select("a").last().text());
                            map.put(HASH_MAP.get(level), submit.get());
                            list.add(map);
                        }
                    }
                }
                executorService.shutdown();
                if (list != null && !list.isEmpty()) {
                    System.out.println("GetCountryAddress:" + list);
                    return list;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private static class GetDowntownList implements Callable<List> {
        private final String url;
        private final int level;
        private final int id;

        public GetDowntownList(String url, int level, int id) {
            this.url = url;
            this.level = level;
            this.id = id;
        }

        @Override
        public List call() throws Exception {
            Document document = null;
            try {
                document = Jsoup.connect(url).timeout(500000).get();
                Elements select = document.select("tr." + HASH_MAP.get(level));
                AtomicInteger id1 = new AtomicInteger(1);

                CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
                for (Element element : select) {
                    ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                    String href = element.select("a").attr("abs:href");
                    if (href != null && !href.isEmpty()) {
                            map.put("id", id1.getAndIncrement());
                            map.put("lid", level);
                            map.put("mid", id);
                            map.put("name", element.select("a").last().text());
                            list.add(map);
                    }
                }
                if (list != null && !list.isEmpty()) {
                    System.out.println("GetDowntownList:" + list);
                    return list;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
