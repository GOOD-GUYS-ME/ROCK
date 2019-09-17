package com.rock.miaosha.system.web;

import com.rock.miaosha.common.utils.CodeImgUtil;
import com.rock.miaosha.common.utils.DateUtils;
import com.rock.miaosha.common.utils.RedisUtils;
import com.rock.miaosha.common.wraper.Wrapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
/**
*@author rock
*@Date 2019/9/17 23:14
*@param
*@return
*
*/
@RestController
public class UtilsController {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    DateUtils dateUtils;

    @RequestMapping("/captcha")
    public void getCaptcha(HttpServletResponse response){
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        CodeImgUtil vCode = new CodeImgUtil(120, 40, 5, 100);
        System.out.println("VCODALFD:"+vCode.getCode());
        redisUtils.set("vCode",vCode.getCode());
        try {
            vCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/getStartTime")
    @ResponseBody
    public Wrapper getActivityStartTime(){
        Wrapper<Object> wrapper = new Wrapper<>();
        try {
            String activityTime = redisUtils.get("activityTime");
            String activityTimeMillis = redisUtils.get("activityTimeMillis");
            System.out.println("activityTime:"+activityTime);
            ArrayList<Object> list = new ArrayList<>();
            list.add(activityTime);
            list.add(activityTimeMillis);
            wrapper.setList(list);
            wrapper.setCode(0);
            wrapper.setMsg("OK");
        } catch (Exception e) {
            e.printStackTrace();
            wrapper.setCode(400);
            wrapper.setMsg("获取活动时间发生异常");
        }
        return wrapper;
    }
    @RequestMapping("/setStartTime")
    @ResponseBody
    public Wrapper setActivityStartTime(String date,String time) {
        System.out.println("date:" + date);
        System.out.println("time:" + time);
        Wrapper<Object> wrapper = new Wrapper<>();
        if (date != null && time != null) {
            try {
                redisUtils.set("activityTime", date + " " + time);
                long l = dateUtils.millis2DateTime1(date + " " + time);
                System.out.println("time1:" + l);
                System.out.println("date:" + date + " " + time);
                redisUtils.set("activityTimeMillis", l+ "");
                wrapper.setT(l);
                wrapper.setCode(0);
                wrapper.setMsg("OK");
                return wrapper;
            } catch (JSONException e) {
                e.printStackTrace();
                wrapper.setCode(400);
                wrapper.setMsg("时间设置失败！");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (date == null) {
            wrapper.setCode(400);
            wrapper.setMsg("日期不能为空！");
            return wrapper;
        } else if (time == null) {
            wrapper.setCode(400);
            wrapper.setMsg("时间不能为空！");
            return wrapper;
        } else {
            wrapper.setCode(400);
            wrapper.setMsg("时间跟日期不能为空！");
            return wrapper;
        }
        return null;
    }



    @RequestMapping("/getEndTime")
    @ResponseBody
    public Wrapper getActivityEndTime(){
        Wrapper<Object> wrapper = new Wrapper<>();
        try {
            String activityEndTime = redisUtils.get("activityEndTime");
            String activityEndTimeMillis = redisUtils.get("activityEndTimeMillis");
            System.out.println("activityEndTime:"+activityEndTime);
            ArrayList<Object> list = new ArrayList<>();
            list.add(activityEndTime);
            list.add(activityEndTimeMillis);
            wrapper.setList(list);
            wrapper.setCode(0);
            wrapper.setMsg("OK");
        } catch (Exception e) {
            e.printStackTrace();
            wrapper.setCode(400);
            wrapper.setMsg("获取活动时间发生异常");
        }
        return wrapper;
    }
    @RequestMapping("/setEndTime")
    @ResponseBody
    public Wrapper setActivityEndTime(String date,String time){
        System.out.println("setActivityEndTime date:"+date);
        System.out.println("setActivityEndTime time:"+time);
        Wrapper<Object> wrapper = new Wrapper<>();
        if (date != null&&time!=null) {
            try {
                redisUtils.set("activityEndTime",date+" "+time);
                long l = dateUtils.millis2DateTime1(date + " " + time);
                redisUtils.set("activityEndTimeMillis",l+"");
                wrapper.setCode(0);
                wrapper.setT(l);
                wrapper.setMsg("OK");
                return wrapper;
            } catch (JSONException e) {
                e.printStackTrace();
                wrapper.setCode(400);
                wrapper.setMsg("时间设置失败！");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (date==null||time==null){
            wrapper.setCode(400);
            wrapper.setMsg("日期或时间不能为空！");
            return wrapper;
        }else{
            wrapper.setCode(400);
            wrapper.setMsg("日期与时间不能为空！");
            return wrapper;
        }
        return null;
    }
}
