package com.test.stream;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * RandomStream class. This is a sample {@link Iterator} based stream.
 *
 * @author <a href="https://plus.google.com/+SureshG">Suresh G</a>
 * @version 1.0
 */
public class RandomStream implements Iterable<Integer> {

    /**
     * Creates a stream out of the iterator.
     *
     * @return Stream of integers.
     */
    public Stream<Integer> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    /**
     * Creates a parallel stream out of the iterator.
     *
     * @return parallel stream of integers.
     */
    public Stream<Integer> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Integer> iterator() {
        final Random rand = new Random();
        final AtomicInteger counter = new AtomicInteger(0);
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                System.out.println("Counter: " + counter.getAndIncrement());
                return rand.nextInt(1000);
            }
        };
    }

    public static void main(String[] args) {
        RandomStream ints = new RandomStream();
        ints.stream().limit(10).forEach(System.out::println);
    }
}
