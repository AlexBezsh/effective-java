package com.alexbezsh.effectivejava.sort;

import java.util.Arrays;
import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Benchmarks {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @OutputTimeUnit(MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 3, warmups = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = MILLISECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = MILLISECONDS)
    public void mergeSortBenchmark(ExecutionPlan plan) {
        MergeSort.sort(plan.array);
    }

    @Benchmark
    @OutputTimeUnit(MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 3, warmups = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = MILLISECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = MILLISECONDS)
    public void insertionSortBenchmark(ExecutionPlan plan) {
        InsertionSort.sort(plan.array);
    }

    @Benchmark
    @OutputTimeUnit(MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 3, warmups = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = MILLISECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = MILLISECONDS)
    public void bubbleSortBenchmark(ExecutionPlan plan) {
        BubbleSort.sort(plan.array);
    }

    @Benchmark
    @OutputTimeUnit(MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 3, warmups = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = MILLISECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = MILLISECONDS)
    public void quickSortBenchmark(ExecutionPlan plan) {
        QuickSort.sort(plan.array);
    }

    @Benchmark
    @OutputTimeUnit(MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 3, warmups = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = MILLISECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = MILLISECONDS)
    public void defaultSortBenchmark(ExecutionPlan plan) {
        Arrays.sort(plan.array);
    }

    @State(Scope.Benchmark)
    public static class ExecutionPlan {

        @Param({"1", "100", "1000", "10000", "100000"})
        private int arrayLength;

        private int[] array;

        @Setup(Level.Invocation)
        public void setUp() {
            Random random = new Random();
            array = new int[arrayLength];
            for (int i = 0; i < arrayLength; i++) {
                array[i] = random.nextInt();
            }
        }

    }

}
