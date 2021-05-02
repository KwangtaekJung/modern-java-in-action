package ch6;

import ch5.Dish;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Grouping {
    public static void main(String[] args) {

        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Caloric dishes grouped by type: " + groupCaloricDishesByType());
    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return Dish.menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<Dish.Type, List<Dish>> groupCaloricDishesByType() {
//        return Dish.menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500, toList())));
    }
}
