package com.alexbezsh.effectivejava.sort;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public abstract class SortUtilTest {

    static Stream<Arguments> sortArgs() {
        return Stream.of(
            Arguments.of(new int[] {}, new int[] {}),
            Arguments.of(new int[] {1}, new int[] {1}),
            Arguments.of(new int[] {4, 1, 2, 3}, new int[] {1, 2, 3, 4}),
            Arguments.of(new int[] {34, 1, 45, 2}, new int[] {1, 2, 34, 45}),
            Arguments.of(new int[] {1, 2, 3, 4, 3}, new int[] {1, 2, 3, 3, 4}),
            Arguments.of(new int[] {-1, 0, 1, 2, 3, 4}, new int[] {-1, 0, 1, 2, 3, 4})
        );
    }

}
