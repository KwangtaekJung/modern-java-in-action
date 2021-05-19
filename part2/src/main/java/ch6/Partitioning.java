package ch6;

import ch5.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Partitioning {

    public static void main(String[] args) {

        // 6.4 분할
        System.out.println("Dishes partitioned by vegetarian: " + partitioningByVegetarian());

        List<Dish> vegetarianDishesByPartitioning = partitioningByVegetarian().get(true);
        System.out.println("Vegetarian dished by partitioning: " + vegetarianDishesByPartitioning);

        List<Dish> vegetarianDishesByFilter = Dish.menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println("Vegetarian dished by filter:" + vegetarianDishesByFilter);

        // 6.4.1 분할의 장점
        System.out.println("Vegetarian dishes by type: " + vegetarianDishesByType());

        System.out.println("Most Caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());

        // Quiz 6.2
        System.out.println(quiz1());
        System.out.println(quiz2());

        // 6.4.2 숫자를 소수와 비소수로 분할하기
        System.out.println("Numbers partitioned in prime and non-prime: " + partitionPrimes(100));

        // 6.5.2 응용하기
        List<Dish> dishesByCustomCollector = Dish.menu.stream().collect(new ToListCollector<Dish>());
        System.out.println("Dishes by custom collector: " + dishesByCustomCollector);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.range(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
                .limit((long) Math.floor(Math.sqrt(candidate)) - 1)
                .noneMatch(i -> candidate % i == 0);
    }

    private static Map<Boolean, Long> quiz2() {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
    }

    private static Map<Boolean, Map<Boolean, List<Dish>>> quiz1() {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,
                partitioningBy(d -> d.getCalories() > 500)));
    }

    private static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian() {
        return Dish.menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get))
        );
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return Dish.menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType)));
    }

    private static Map<Boolean, List<Dish>> partitioningByVegetarian() {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }
}
