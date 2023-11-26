package com.alexbezsh.effectivejava.binarytreebypass;

import java.util.function.Consumer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BinaryTreeUtils {

    public static <T> void bypass(Node<T> root, Consumer<T> consumer) {
        if (root != null) {
            bypass(root.getLeft(), consumer);
            consumer.accept(root.getValue());
            bypass(root.getRight(), consumer);
        }
    }

    /**
     * <pre>                     25                           </pre>
     * <pre>           12                    36               </pre>
     * <pre>      6         18          31         42         </pre>
     * <pre>    1   8     16  22     28   33     38  45       </pre>
     * <pre>          9            26                   48    </pre>
     *
     * @return root node of a test binary tree
     */
    public static Node<Integer> testBinaryTree() {
        return Node.<Integer>builder()
            .value(25)
            .left(Node.<Integer>builder()
                .value(12)
                .left(Node.<Integer>builder()
                    .value(6)
                    .left(Node.<Integer>builder().value(1).build())
                    .right(Node.<Integer>builder()
                        .value(8)
                        .right(Node.<Integer>builder().value(9).build())
                        .build())
                    .build())
                .right(Node.<Integer>builder()
                    .value(18)
                    .left(Node.<Integer>builder().value(16).build())
                    .right(Node.<Integer>builder().value(22).build())
                    .build())
                .build())
            .right(Node.<Integer>builder()
                .value(36)
                .left(Node.<Integer>builder()
                    .value(31)
                    .left(Node.<Integer>builder()
                        .value(28)
                        .left(Node.<Integer>builder().value(26).build())
                        .build())
                    .right(Node.<Integer>builder().value(33).build())
                    .build())
                .right(Node.<Integer>builder()
                    .value(42)
                    .left(Node.<Integer>builder().value(38).build())
                    .right(Node.<Integer>builder()
                        .value(45)
                        .right(Node.<Integer>builder().value(48).build())
                        .build())
                    .build())
                .build())
            .build();
    }

}
