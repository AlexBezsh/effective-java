package com.alexbezsh.effectivejava.cache.custom;

public interface RemovalListener<K, V> {

    void removed(RemovalInfo<K, V> info);

}
