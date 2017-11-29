package com.ulyssesss.java8example.lambda;

@FunctionalInterface
public interface MyPredicate<T> {
    boolean test(T t);
}
