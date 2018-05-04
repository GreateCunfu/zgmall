package com.op.marvel.dc.zhg38.common.source.jedis;

import java.util.Set;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 17:40 on 29/03/2018.
 */
public interface JedisClient {

    String set(String key, String value);

    String get(String key);

    Boolean exists(String key);

    Long expire(String key, int seconds);

    Long ttl(String key);

    Long incr(String key);

    Long hset(String key, String filed, String value);

    String hget(String key, String filed);

    Long hdel(String key, String... field);

    Set<String> keys(String pattern);
}
