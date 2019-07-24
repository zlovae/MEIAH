package com.meiya.got.util;

import com.meiya.got.po.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class JedisUtil implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);
    private JedisPool pool;

    public static void print(int index, Object obj) {
        System.out.println(String.format("%d, %s", index, obj.toString()));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("redis://47.103.118.92:6379/10");
    }

    public long hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hset(key, field, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public long hincrby(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hincrBy(key, field, 1);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public long hdecrby(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.hincrBy(key, field, -1);
            System.out.println(jedis.hgetAll(key));
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public Map<String, String> delall(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Map<String, String> map = jedis.hgetAll(key);
            for(String s : map.keySet()) {
                jedis.hdel(key, s);
            }
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public Map<String, String> getall(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Map<String, String> map = new HashMap<>();
            map.putAll(jedis.hgetAll(key));
            System.out.println(map);
            return map;
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> sgetall(String key) {
        Jedis jedis = null;
        try {
            //return jedis.s
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
        return null;
    }

//    public List<String> sgetall(String key) {
//        Jedis jedis = null;
//        try {
//            //return jedis.s
//        } catch (Exception e) {
//            logger.error("发生异常" + e.getMessage());
//        } finally {
//            if(jedis!=null) {
//                jedis.close();
//            }
//        }
//        return null;
//    }
}
