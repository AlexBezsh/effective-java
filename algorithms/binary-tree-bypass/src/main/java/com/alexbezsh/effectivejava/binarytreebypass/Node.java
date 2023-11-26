package com.alexbezsh.effectivejava.binarytreebypass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Node<V> {

    private V value;
    private Node<V> left;
    private Node<V> right;

}
