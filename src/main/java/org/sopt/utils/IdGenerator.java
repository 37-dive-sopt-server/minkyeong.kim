package org.sopt.utils;

public class IdGenerator {

    private static long counter = 1;

    private IdGenerator() {}

    public static synchronized long next() {
        return counter++;
    }
}