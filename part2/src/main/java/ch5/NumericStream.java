package ch5;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class NumericStream {
    public static void main(String[] args) {

        // 5.7 숫자형 스트림
        // 5.7.1 기본형 특화 스트림
        Integer calories = Dish.menu.stream()
                .map(dish -> dish.getCalories())
                .reduce(0, Integer::sum);
        System.out.println(calories);

        int calories2 = Dish.menu.stream()
                .mapToInt(dish -> dish.getCalories())
                .sum();
        System.out.println(calories2);

        // max와 OptionalInt
        OptionalInt maxCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max;
        if (maxCalories.isPresent()) {
            max = maxCalories.getAsInt();
        } else {
            max = 1;
        }
        System.out.printf("Max caloric : %d\n", max);
        System.out.printf("Max caloric : %d\n", maxCalories.orElse(1));
        System.out.println(maxCalories);

        IntStream evenNumbers = IntStream.range(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

    }
}
