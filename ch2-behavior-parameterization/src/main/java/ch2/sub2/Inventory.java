package ch2.sub2;

import ch2.sub2.applefilter.Apple;
import ch2.sub2.applefilter.Color;

import java.util.Arrays;
import java.util.List;

public class Inventory {
    private static List<Apple> inventory = Arrays.asList(
            new Apple(Color.RED, 150),
            new Apple(Color.GREEN, 200),
            new Apple(Color.YELLOW, 250),
            new Apple(Color.GREEN, 140)
    );

    public static List<Apple> getInstance() {
        return inventory;
    }
}
