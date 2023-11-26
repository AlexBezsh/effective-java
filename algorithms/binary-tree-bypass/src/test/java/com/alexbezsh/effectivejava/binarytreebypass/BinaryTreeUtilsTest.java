package com.alexbezsh.effectivejava.binarytreebypass;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeUtilsTest {

    @Test
    void binaryTreeBypassTest() {
        Node<Integer> root = BinaryTreeUtils.testBinaryTree();
        List<Integer> expected =
            List.of(1, 6, 8, 9, 12, 16, 18, 22, 25, 26, 28, 31, 33, 36, 38, 42, 45, 48);
        List<Integer> actual = new ArrayList<>();

        BinaryTreeUtils.bypass(root, actual::add);

        assertEquals(expected, actual);
    }

}