package com.test.stream;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Test class
 *
 * @author <a href="https://plus.google.com/+SureshG">Suresh G</a>
 * @version 1.0
 */
public class StreamTest {

    public static void main(String[] args) {
        long s = System.nanoTime();
        Arrays.asList(1, 2, 3).stream().map(StreamTest::tx).forEach(System.out::println);
        IntStream.range(1, 10).parallel().map(StreamTest::tx).forEach(System.out::println);

        long e = System.nanoTime();
        System.out.println("Time taken : " + TimeUnit.MILLISECONDS.convert((e - s), TimeUnit.NANOSECONDS));

    }

    public static int tx(int input) {
        System.out.println("Got input " + input);
        return input * 2;
    }
}
