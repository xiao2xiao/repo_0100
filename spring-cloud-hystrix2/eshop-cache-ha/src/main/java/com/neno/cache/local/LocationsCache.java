package com.neno.cache.local;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2019/3/18 0:01
 */
public class LocationsCache {
    private static Map<Long, String> shopNames = new HashMap<>();

    static {
        shopNames.put(1L, "长安街区");
    }

    public static String getShopName(Long shopId) {
        return shopNames.get(shopId);
    }

}
