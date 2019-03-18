package com.neno.cache.local;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2019/3/18 0:01
 */
public class BrandCache {
    private static Map<Long, String> brandNames = new HashMap<>();

    static {
        brandNames.put(1L, "杂牌");
    }

    public static String getBrandName(Long brandId) {
        return brandNames.get(brandId);
    }

}
