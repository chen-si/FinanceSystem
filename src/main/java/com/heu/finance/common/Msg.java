package com.heu.finance.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回给浏览器ajax请求的数据格式
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
public class Msg {
    //封装json数据的通用类
    private Integer code;

    //返回浏览器的数据
    private Map<String,Object> extend = new HashMap<>();

    /**
     * 处理成功
     * @return code = 100 代表成功
     */
    public static Msg success(){
        Msg result = new Msg();
        result.code = 100;
        return result;
    }

    /**
     * 给Msg添加一个 key-value 对
     * @param key key
     * @param val value
     * @return 发挥添加k-v后的Msg
     */
    public Msg add(String key, Object val){
        this.extend.put(key,val);
        return this;
    }

    /**
     * 处理失败
     * @return code = 200 失败
     */
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
