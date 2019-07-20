package com.meiya.got.util;

public class RedisKeyUtil {
    private static String SPILIT = ",";
    private static String CART = "CART";

    public static String getCartKey(String phone, Long storeId) {
        return CART + SPILIT + phone + SPILIT + storeId;
    }

    public static String getCartField(Long productId) {
        return String.valueOf(productId);
    }

}
