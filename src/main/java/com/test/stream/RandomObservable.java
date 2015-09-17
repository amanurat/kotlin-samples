/*
 * Copyright 2014-2015 WalmartLabs.
 */
package com.test.stream;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RandomObservable class
 *
 * @author <a href="mailto:sgopal1@walmartlabs.com">Suresh G</a>
 * @version 1.0
 */
public class RandomObservable extends Observable<Integer> {
    public RandomObservable() {
        super(subscriber -> {
            try {
                Random rand = new Random();
                int val;
                while (!subscriber.isUnsubscribed()) {
                    val = rand.nextInt(5000);
                    System.out.println("->>> Value : " + val + ", " + Thread.currentThread());
                    subscriber.onNext(val);
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
            subscriber.onCompleted();

        });
    }

    public static void main(String[] args) {
        RandomObservable o = new RandomObservable();
        CountDownLatch latch = new CountDownLatch(1);
        AtomicInteger count = new AtomicInteger(0);

        o.subscribeOn(Schedulers.io()).limit(100).subscribe(i -> {
            count.incrementAndGet();
            System.out.println("<<<- Value : " + i + ", " + Thread.currentThread());
        }, Throwable::printStackTrace, latch::countDown);

        System.out.println("Started waiting..");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiting is done! " + count.get());
    }
}
