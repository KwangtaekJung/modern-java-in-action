package ch4;

import java.util.*;
import java.util.stream.Collectors;

public class StreamBasic {
    public static void main(String[] args) {

        getLowCaloricDishesNameInJava7(Dish.menu).forEach(System.out::println);

        getLowCaloricDishesNameInJava8(Dish.menu).forEach(System.out::println);
    }

    private static List<String> getLowCaloricDishesNameInJava8(List<Dish> dishes) {
        return dishes.parallelStream()

                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    private static List<String> getLowCaloricDishesNameInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : dishes) {
            if(d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        for(Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }
}
