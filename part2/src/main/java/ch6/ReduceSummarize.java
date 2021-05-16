package ch6;

import ch5.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ReduceSummarize {
    public static void main(String[] args) {

        // 6.2 Reducing and Summarizing
        // Sum
        Integer sum = Dish.menu.stream().map(Dish::getCalories).reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        Integer sum2 = Dish.menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(sum2);;

        int sum3 = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(sum3);

        Integer sum4 = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(sum4);

        Optional<Integer> sumOptional = Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum);
        sumOptional.ifPresent(System.out::println);

        // Max
        Integer max1 = Dish.menu.stream().map(Dish::getCalories).reduce(0, Integer::max);
        System.out.println(max1);

        OptionalInt maxOptional = Dish.menu.stream().mapToInt(Dish::getCalories).max();
        maxOptional.ifPresent(System.out::println);

        Comparator<Dish> dishCaloriesCompartor = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> maxOptional2 = Dish.menu.stream().collect(maxBy(dishCaloriesCompartor));
        maxOptional2.ifPresent(System.out::println);

        // etc
        Double avg = Dish.menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avg);

        // Summarizing
        IntSummaryStatistics summaryStatistics = Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics);

        // 6.2.3 문자열 연결
        String names = Dish.menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(names);

        // 6.2.4 범용 리듀싱 요약 연산
        Integer reducingSum = Dish.menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(reducingSum);

        Integer reducingSum2 = Dish.menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(reducingSum2);

        // 퀴즈 6-1 리듀싱으로 문자열 연결하기
        String names1 = Dish.menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + ", " + s2));
        System.out.println(names1);

        String names2 = Dish.menu.stream().map(Dish::getName).collect(reducing((s1, s2) -> s1 + ", " + s2)).get();
        System.out.println(names2);
    }
}
