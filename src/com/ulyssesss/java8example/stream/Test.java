package com.ulyssesss.java8example.stream;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        List<Dish> lowCalories = menu.stream().filter(dish -> dish.getCalories() < 500)
                .limit(3).collect(Collectors.toList());
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 4, 4, 5, 6);
        List<Integer> evenNumbers = numbers.stream().distinct().filter(i -> i % 2 == 0).collect(Collectors.toList());
        List<Integer> skip2even = numbers.stream().filter(i -> i % 2 == 0).skip(2).collect(Collectors.toList());

        List<String> dishNames = menu.stream().map(Dish::getName).collect(Collectors.toList());

        List<String> uniqueCharacters = Stream.of("hello", "world")
                .map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());

        List<Integer> a = Arrays.asList(1, 2);
        List<Integer> b = Arrays.asList(3, 4, 5);
        List<int[]> result = a.stream().flatMap(i -> b.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());

        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::print);

        int sum = Stream.of(1, 3, 5, 7).reduce(0, (i, j) -> i + j);
        Optional<Integer> biggest = Stream.of(1, 3, 5, 6).reduce(Integer::max);

        int calories = menu.stream().mapToInt(Dish::getCalories).sum();
        OptionalInt maxCalories1 = menu.stream().mapToInt(Dish::getCalories).max();
        int maxCalories2 = menu.stream().mapToInt(Dish::getCalories).max().orElse(1);

        System.out.println(IntStream.range(1, 100)); // 1 ~ 99
        System.out.println(IntStream.rangeClosed(1, 100)); // 1 ~ 100

        Stream<double[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(
                        i -> IntStream.rangeClosed(i, 100)
                                .mapToObj(j -> new double[]{i, j, Math.sqrt(i * i + j * j)})
                                .filter(k -> k[2] % 1 == 0)
                );

        try (Stream<String> lines = Files.lines(Paths.get("test.txt"), Charset.defaultCharset())) {
            long uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        } catch (Exception e) {
        }

        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20).map(t -> t[0]).forEach(System.out::println);
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        long howManyDishes = menu.stream().collect(Collectors.counting());
        Optional<Dish> mostCalorieDish1 = menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
        Optional<Dish> mostCalorieDish2 = menu.stream().max(Comparator.comparingInt(Dish::getCalories));

        double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        String dishes = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));

        int totalCalories = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        Map<String, List<Dish>> dishesByCal = menu.stream().collect(Collectors.groupingBy(
                dish -> dish.getCalories() > 700 ? "fat" : dish.getCalories() < 400 ? "normal" : "diet"));

        Map<Dish.Type, Map<String, List<Dish>>> dishesByTypeCal = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> dish.getCalories() > 700
                        ? "fat" : dish.getCalories() < 400 ? "normal" : "diet")));
        Map<Dish.Type, Long> typesCount = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        Map<Dish.Type, Dish> mostCalByType = menu.stream().collect(Collectors.groupingBy(Dish::getType
                , Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));

        Map<Dish.Type, Set<String>> calsByType = menu.stream().collect(Collectors.groupingBy(Dish::getType
                , Collectors.mapping(dish -> dish.getCalories() > 700
                        ? "fat" : dish.getCalories() < 400 ? "normal" : "diet", Collectors.toSet())));

        Collector<Object, ?, HashSet<Object>> toList = Collectors.toCollection(HashSet::new);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream().collect(
                Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
    }
}
