package ch3.sub7;

import ch3.sub6.methodreference.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Application {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(Color.RED, 80 ),
                new Apple(Color.GREEN, 155),
                new Apple(Color.RED, 120)
        );

        System.out.println(inventory.toString());
//        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory.toString());

        // Comparator 조합
        inventory.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));

        // Predicate 조합
    }
}
