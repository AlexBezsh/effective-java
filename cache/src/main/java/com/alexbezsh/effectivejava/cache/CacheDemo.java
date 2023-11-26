package com.alexbezsh.effectivejava.cache;

import com.alexbezsh.effectivejava.cache.common.Cache;
import com.alexbezsh.effectivejava.cache.common.CachedObject;
import com.alexbezsh.effectivejava.cache.custom.CustomCache;
import com.alexbezsh.effectivejava.cache.guava.GuavaCache;

public class CacheDemo {

    public static void main(String[] args) {
        int cacheSize = 1;
        System.out.printf("Cache size: %s%n%n", cacheSize);

        System.out.println("Guava cache");
        cacheDemo(GuavaCache.<String, CachedObject>builder().maxSize(cacheSize).build());
        System.out.println();

        System.out.println("Custom cache");
        cacheDemo(CustomCache.<String, CachedObject>builder().maxSize(cacheSize).build());
    }

    private static void cacheDemo(Cache<String, CachedObject> cache) {
        String key1 = "key1";
        String value1 = "value1";
        System.out.printf("Adding new entry: key - %s, value - %s%n", key1, value1);
        cache.put(key1, new CachedObject(value1));

        System.out.printf("Getting value by key '%s': %s%n", key1, cache.get(key1).getValue());
        System.out.println("Adding another key-value pair, so previous one will be evicted");
        cache.put("key2", new CachedObject("value2"));
    }

}
