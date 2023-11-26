package com.alexbezsh.effectivejava.cache.common;

public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    double getAverageInsertionTime();

    long getEvictionsCount();

}
