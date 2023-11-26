package com.alexbezsh.effectivejava.binarysearch;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class Benchmarks {

    private static final int TEST_ARRAY_LENGTH = 1_000_000_000;
    private static final int[] TEST_ARRAY = new int[TEST_ARRAY_LENGTH];

    static {
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            TEST_ARRAY[i] = i;
        }
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @OutputTimeUnit(NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 3, warmups = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = MILLISECONDS)
    public void iterativeSearchBenchmark(ExecutionPlan plan) {
        BinarySearch.iterative(TEST_ARRAY, plan.value);
    }

    @Benchmark
    @OutputTimeUnit(NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 3, warmups = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = MILLISECONDS)
    public void recursiveSearchBenchmark(ExecutionPlan plan) {
        BinarySearch.recursive(TEST_ARRAY, plan.value);
    }

    @State(Scope.Benchmark)
    public static class ExecutionPlan {

        @Param({"-1", "1", "123456", "123456789", "987654321"})
        private int value;

    }

}
