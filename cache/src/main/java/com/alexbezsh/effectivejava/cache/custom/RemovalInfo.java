package com.alexbezsh.effectivejava.cache.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RemovalInfo<K, V> {

    private final K key;
    private final V value;
    private final RemovalReason reason;

}
