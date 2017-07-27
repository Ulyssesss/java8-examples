package com.ulyssesss.java8.examples.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by jiangyue on 2017/7/26.
 */
public class Lambda {
    public static void main(String[] args) {

        List<Apple> appleList = Arrays.asList(new Apple(80, "green")
                , new Apple(160, "green"), new Apple(200, "red"));

        //  [Apple{weight=80, color='green'}, Apple{weight=160, color='green'}, Apple{weight=200, color='red'}]
        System.out.println(appleList);

        //  [Apple{weight=80, color='green'}, Apple{weight=160, color='green'}]
        List<Apple> greenApple1 = filterApples(appleList, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApple1);

        //  [Apple{weight=160, color='green'}, Apple{weight=200, color='red'}]
        List<Apple> heavyApple1 = filterApples(appleList, a -> a.getWeight() > 80);
        System.out.println(heavyApple1);

        //  [Apple{weight=160, color='green'}]
        List<Apple> heavyGreenApple = filterApples(appleList, a -> a.getWeight() > 80 && "green".equals(a.getColor()));
        System.out.println(heavyGreenApple);

        //  [Apple{weight=80, color='green'}, Apple{weight=160, color='green'}]
        List<Apple> greenApple2 = filterApples(appleList, Apple::isGreen);
        System.out.println(greenApple2);

        //  [Apple{weight=160, color='green'}, Apple{weight=200, color='red'}]
        List<Apple> heavyApple2 = filterApples(appleList, Apple::isHeavy);
        System.out.println(heavyApple2);

    }

    private static List<Apple> filterApples(List<Apple> appleList, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : appleList) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
