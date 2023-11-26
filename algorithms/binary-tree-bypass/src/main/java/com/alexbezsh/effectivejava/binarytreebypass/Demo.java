package com.alexbezsh.effectivejava.binarytreebypass;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        Node<Integer> binaryTreeRoot = BinaryTreeUtils.testBinaryTree();

        // to console
        BinaryTreeUtils.bypass(binaryTreeRoot, System.out::println);

        // to list
        List<Integer> result = new ArrayList<>();
        BinaryTreeUtils.bypass(binaryTreeRoot, result::add);
        System.out.println(result);
    }

}
