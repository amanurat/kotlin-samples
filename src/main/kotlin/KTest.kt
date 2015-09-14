package com.test
/**
 * KTest class
 *
 * @author <a href="mailto:sgopal1@walmartlabs.com">Suresh G</a>
 * @version 1.0
 */

fun main(args: Array<String>) {

    // Eager
    listOf(1,2,3).map { println("Got input ${it}")
                        it * 2 }.forEach { println(it) }

    // Lazy
    sequenceOf(1,2,3).map { println("Got Seq input ${it}")
        it * 2 }.forEach { println(it) }
}
