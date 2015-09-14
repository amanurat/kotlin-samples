package com.test.stream;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * RandomStream class
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
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Integer> iterator() {
        final Random rand = new Random();
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return rand.nextInt(1000);
            }
        };
    }
}
