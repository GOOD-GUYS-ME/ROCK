package com.rock.miaosha.system.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test6 {
    public static void main(String[]args) throws ExecutionException, InterruptedException, IOException {
        String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/46/4604.html";
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService service=new ExecutorCompletionService(executorService);
//        Future submit = service.submit(new GetProvinceAddress());
//        Object o = submit.get();
//        System.out.println("o:"+o);
        Document document = Jsoup.connect(URL).timeout(5000).get();
        Elements a = document.select("tr.towntr");
        for (Element element : a) {
            System.out.println("element:"+element.select("a").last().text());
        }
        executorService.shutdown();
    }

    private static class GetProvinceAddress implements Callable<List>{
        @Override
        public List call() throws Exception {
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            CompletionService service=new ExecutorCompletionService(executorService);
            Future submit = service.submit(new GetCityAddress());
            Object o = submit.get();
            ArrayList<Object> list = new ArrayList<>();
            for (int i = 0; i <8 ; i++) {
                ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                map.put("id",i);
                map.put("name","province"+i);
                map.put("city",o);
                list.add(map);
            }
            return list;
        }
    }
    private static class GetCityAddress implements Callable<List>{
        @Override
        public List call() throws Exception {
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            CompletionService service=new ExecutorCompletionService(executorService);
            Future submit = service.submit(new GetCountryAddress());
            Object o = submit.get();
            ArrayList<Object> list = new ArrayList<>();
            for (int i = 0; i <8 ; i++) {
                ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                map.put("id",i);
                map.put("name","city"+i);
                map.put("country",o);
                list.add(map);
            }
            return list;
        }
    }
    private static class GetCountryAddress implements Callable<List>{
        @Override
        public List call() throws Exception {
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            CompletionService service=new ExecutorCompletionService(executorService);
            Future submit = service.submit(new GetDowntownAddress());
            Object o = submit.get();
            ArrayList<Object> list = new ArrayList<>();
            for (int i = 0; i <8 ; i++) {
                ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                map.put("id",i);
                map.put("name","country"+i);
                map.put("downtown",o);
                list.add(map);
            }
            return list;
        }
    }
    private static class GetDowntownAddress implements Callable<List>{
        @Override
        public List call() throws Exception {
            ArrayList<Object> list = new ArrayList<>();
            for (int i = 0; i <8 ; i++) {
                ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
                map.put("id",i);
                map.put("name","downtown"+i);
                list.add(map);
            }
            return list;
        }
    }
}
