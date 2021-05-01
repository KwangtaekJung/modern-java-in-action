package ch2.sub2.examples;

import ch2.sub2.Inventory;
import ch2.sub2.applefilter.Apple;

import java.util.Comparator;
import java.util.List;

public class AppComparator {
    public static void main(String[] args) {

        // Comparator + 익명 함수
        List<Apple> inventory = Inventory.getInstance();
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return Integer.compare(o1.getWight(),o2.getWight());
            }
        });

        System.out.println(inventory.toString());

        // Comparator + 람다
        List<Apple> inventory2 = Inventory.getInstance();
        inventory2.sort((Apple a1, Apple a2) -> Integer.valueOf(a1.getWight()).compareTo(Integer.valueOf(a2.getWight())));
        System.out.println(inventory2.toString()
        );
    }
}
