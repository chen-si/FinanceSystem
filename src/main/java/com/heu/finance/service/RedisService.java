package com.heu.finance.service;

public interface RedisService {
    /**
     * 存储数据
     * @param key key
     * @param value value
     */
    void set(String key,String value);

    /**
     * 获取数据
     * @param key key
     * @return 返回value
     */
    String get(String key);

    /**
     * 设置超时时间
     * @param key key
     * @param expire 超时时间
     * @return 返回操作是否成功
     */
    boolean expire(String key,long expire);

    /**
     * 删除数据
     * @param key key
     */
    void remove(String key);

    /**
     * 自增操作
     * @param key key
     * @param delta 自增步长
     * @return 自增结果
     */
    Long increment(String key,long delta);
}
