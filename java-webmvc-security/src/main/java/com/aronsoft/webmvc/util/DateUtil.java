package com.aronsoft.webmvc.util;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static Date getTime(String time){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        try {
            return format.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }

    public static LocalDateTime getLocalTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        try {
            return LocalDateTime.parse(time, formatter);
        }catch (Exception e){
            return null;
        }
    }
}
