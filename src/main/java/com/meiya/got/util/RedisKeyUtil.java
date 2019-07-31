package com.meiya.got.util;

public class RedisKeyUtil {
    private static String SPILIT = ",";
    private static String CART = "CART";
    private static String SECKILL_EVENT = "SECKILL_EVENT";
    private static String SECKILL_GOOD_VO = "SECKILL_GOOD_VO";//秒杀对象缓存
    private static String SECKILL_GOOD_STOCK = "SECKILL_GOOD_STOCK";//秒杀库存缓存
    private static String SECKILL_ORDER = "SECKILL_ORDER";//秒杀订单缓存
    private static String SECKILL_PATH = "SECKILL_PATH";
    private static String SECKILL_VISIT_TIMES = "SECKILL_VISIT_TIMES";
    private static String ONLINE_USER = "ONLINE_USER";
    private static String ONLINE_STORE = "ONLINE_STORE";
    //private static String

    public static String getOnlineUserKey() {
        return ONLINE_USER;
    }

    public static String getOnlineStoreKey() {
        return ONLINE_STORE;
    }

    public static String getCartKey(String phone, Long storeId) {
        return CART + SPILIT + phone + SPILIT + storeId;
    }

    public static String getCartField(Long productId) {
        return String.valueOf(productId);
    }

    public static String getSeckillEventKey() {
        return SECKILL_EVENT;
    }

    public static String getSeckillStockKey(Long goodId) {
        return SECKILL_GOOD_STOCK + SPILIT + goodId;
    }

    public static String getSeckillGoodVoKey() {
        return SECKILL_GOOD_VO;
    }

    public static String getSeckillOrderKey() {
        return SECKILL_ORDER;
    }

    public static String getSeckillOrderValue(Long userId, Long skFoodId) {
        return userId + SPILIT + skFoodId;
    }

    public static String getSeckillPathKey() {
        return SECKILL_PATH;
    }

    public static String getSeckillPathField(Long userId, Long skId) {
        return userId + SPILIT + skId;
    }

    public static String getSeckillVisitTimesKey(Long userId) {
        return SECKILL_VISIT_TIMES + SPILIT + userId;
    }

}
