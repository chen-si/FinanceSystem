package com.heu.finance.common;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    //封装json数据的通用类
    private Integer code;

    //返回浏览器的数据
    private Map<String,Object> extend = new HashMap<>();

    //处理成功
    public static Msg success(){
        Msg result = new Msg();
        result.code = 100;
        return result;
    }

    public Msg add(String key, Object val){
        this.extend.put(key,val);
        return this;
    }

    public static Msg success(String key, Object val){
        Msg result = new Msg();
        result.code = 100;
        result.extend.put(key,val);
        return result;
    }

    public static Msg failed(){
        Msg result = new Msg();
        result.code = 200;
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
