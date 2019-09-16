package com.rock.miaosha.common.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
@Component
public class DateUtils {
    public String dateTime2Millis(long millisTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(millisTime);
    }
    public String dateTime2Millis1(long millisTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return simpleDateFormat.format(millisTime);
    }
    public long millis2DateTime(String dateTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(dateTime).getTime();
    }
    public long millis2DateTime1(String dateTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return simpleDateFormat.parse(dateTime).getTime();
    }
}
