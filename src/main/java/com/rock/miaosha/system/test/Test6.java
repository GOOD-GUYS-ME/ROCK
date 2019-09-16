package com.rock.miaosha.system.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test6 {
    public static void main(String[]args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService service=new ExecutorCompletionService(executorService);
        Future submit = service.submit(new GetProvinceAddress());
        Object o = submit.get();
        System.out.println("o:"+o);
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
