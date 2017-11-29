package com.ulyssesss.java8example.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Test {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(120, "red"), new Apple(160, "green"));
        List<Apple> greenApples = filter(apples, a -> "green".equals(a.getColor()));
        Runnable runnable = () -> {
            System.out.println("hello, lambda");
            System.out.println("hi, lambda");
        };

        Predicate<Apple> predicate1 = a -> "red".equals(a.getColor());
        Predicate<Apple> predicate2 = (Apple a) -> "red".equals(a.getColor());
        BiPredicate<String, Integer> biPredicate = (a, b) -> a.equals(String.valueOf(b));

        Consumer<Apple> consumer = a -> System.out.println("apple's color is " + a.getColor());

        Function<Integer, String> function1 = String::valueOf;
        IntToDoubleFunction function2 = Double::valueOf;

        Supplier<Apple> supplier = () -> new Apple(110, "green");

        UnaryOperator<String> operator1 = a -> a + a;
        BinaryOperator<String> operator2 = (a, b) -> a + b;

        Predicate<Apple> green = a -> "green".equals(a.getColor());
        Predicate<Apple> weight = Apple::isWeight;
        Predicate<Apple> weightNotGreen = green.negate().and(weight);

        Function<Integer, Integer> f = x -> x + 2;
        Function<Integer, Integer> g = x -> x * x;
        Function<Integer, Integer> h = f.andThen(g); //g(f(x))
        Function<Integer, Integer> i = f.compose(g); //f(g(x))

    }

    private static List<Apple> filter(List<Apple> apples, MyPredicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
