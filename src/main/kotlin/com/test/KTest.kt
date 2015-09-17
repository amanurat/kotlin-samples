package com.test

import com.test.stream.RandomObservable
import com.test.stream.RandomStream
import rx.Observer
import rx.schedulers.Schedulers
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.IntStream
import kotlin.concurrent.currentThread

/**
 * Kotlin Sample class
 *
 * @author <a href="https://plus.google.com/+SureshG">Suresh G</a>
 * @version 1.0
 */

fun main(args: Array<String>) {

    val s = System.nanoTime()
    // Eager
    listOf(1, 2, 3).map {
        println("Got input ${it}")
        it * 2
    }.forEach { println(it) }

    // Lazy (sequenceOf())
    (1..10).asSequence().map {
        println("Got Seq input ${it}")
        it * 2
    }.forEach { println(it) }

    val e = System.nanoTime()

    println("Kotlin Time taken : ${MILLISECONDS.convert((e - s), NANOSECONDS)}")

    IntStream.range(1, 10).map {
        println("Got ${it}")
        it * 2
    }.forEach { println(it) }

    RandomStream().stream().limit(5).map {
        println("Got from RandomStream: ${it}")
        it * 2
    }.forEach { println(it) }


    val o = RandomObservable()
    var count = 0
    val latch = CountDownLatch(1)

    o.observeOn(Schedulers.computation()).limit(34).subscribe(object : Observer<Int> {
        override fun onError(e: Throwable?) {
            e?.printStackTrace()
        }

        override fun onNext(t: Int?) {
            println("<<<-- $t - $currentThread")
            count++
        }

        override fun onCompleted() {
            println("Completed")
            latch.countDown()
        }

    })
    latch.await()
    println("Got ${count} messages")
}
