/*
 * Copyright 2014-2015 WalmartLabs.
 */

import java.util.Arrays;

/**
 * Test class
 *
 * @author <a href="mailto:sgopal1@walmartlabs.com">Suresh G</a>
 * @version 1.0
 */
public class Test {

    public static void main(String[] args) {
        Arrays.asList(1, 2,3).stream().map(Test::tx).forEach(System.out::println);
    }

    public static int tx(int input) {
        System.out.println("Got input " + input);
        return  input * 2;
    }
}
