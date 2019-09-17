package com.rock.miaosha.system.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
/**
*@author rock
*@Date 2019/9/17 23:12
*@param
*@return
*
*/
public class FutureTest {


    public static void main(String[]args) throws BrokenBarrierException, InterruptedException, ParseException {
//        AtomicInteger atomicInteger=new AtomicInteger(5000);
//        int cpu_count=Runtime.getRuntime().availableProcessors();
//        long l = System.currentTimeMillis();
//        System.out.println("开始计时");
//        ExecutorService executorService = Executors.newFixedThreadPool(cpu_count);
//        for (int i = 0; i <cpu_count ; i++) {
//            FutureTask<Boolean> task = new FutureTask<>(new ReduceGoodsAmount(atomicInteger));
//            executorService.submit(task);
//        }
//        System.out.println("总共耗时："+(System.currentTimeMillis()-l));
//        executorService.shutdown();

//        Date date = new Date(2019,8,22);
//        long time = date.getTime();
//        System.out.println("time:"+time);
//        Date d = new Date();
//        System.out.println("time1:"+d.getTime());
//        LocalDateTime now = LocalDateTime.now();
//        LocalTime localTime = now.toLocalTime();
//        LocalDate localDate = now.toLocalDate();
//        System.out.println("localDate："+localDate);
//        System.out.println("localTime："+localTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = simpleDateFormat.parse("2019-08-26 01:06:00").getTime();
        String format = simpleDateFormat.format(time);
        String c = simpleDateFormat.format(System.currentTimeMillis());
        long time1 = simpleDateFormat.parse(format).getTime();
        String format1 = simpleDateFormat.format(System.currentTimeMillis());
        long time2 = simpleDateFormat.parse(format1).getTime();
        String format2 = simpleDateFormat.format(time2);
        System.out.println("time:"+time);
        System.out.println("time1:"+time1);
        System.out.println("time2:"+time2);
        System.out.println("format:"+format);
        System.out.println("format1:"+format1);
        System.out.println("format2:"+format2);
        System.out.println("currentTimeMillis:"+c);
    }

    private static class ReduceGoodsAmount implements Callable<Boolean>{
        private final AtomicInteger amount;

        public ReduceGoodsAmount(AtomicInteger amount) {
            this.amount = amount;
        }

        @Override
        public Boolean call() throws Exception {
            while (amount.get()>0){
                int andDecrement = amount.getAndDecrement();
                System.out.println("当前正被抢购商品剩余数量amount:"+andDecrement+",当前线程："+Thread.currentThread().getName());
            }
            return true;
        }
    }
}
