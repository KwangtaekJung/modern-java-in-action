package ch3.sub6.methodreference;

import ch3.sub7.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Application {
    public static void main(String[] args) {

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);

        System.out.println(apples.toString());

        BiFunction<Color, Integer, Apple> c = Apple::new;
        Apple apple = c.apply(Color.GREEN, 100);
        System.out.println(apple.toString());
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for(Integer i : list) {
            result.add(f.apply(i));
        }
        return result;
    }
}
