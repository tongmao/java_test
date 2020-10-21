package com.tongmao.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class StreamUtils {

    private StreamUtils() {}

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String getJSONString(Object o){
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    public static String getJSONString(List list){
        try {
            return OBJECT_MAPPER.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <V> V getObject(InputStream is,Class<V> c){
        V v = null;
        try {
            v =  OBJECT_MAPPER.readValue(is, c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    }

    public static String getString(InputStream is) {
        String str="";
        BufferedInputStream bis = new BufferedInputStream(is);
        int l;
        byte[] buffer = new byte[1024];
        try {
            while((l=bis.read(buffer))>0){
                str+=new String(buffer,0,l);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

}
