package ch2.sub2.applefilter;

import ch2.sub2.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Application {
    public static void main(String[] args) {

//        List<Apple> inventory = Arrays.asList(
//                new Apple(Color.RED, 150),
//                new Apple(Color.GREEN, 200),
//                new Apple(Color.YELLOW, 250),
//                new Apple(Color.GREEN, 140));

        // 객체 참조
        List<Apple> greenApples = filterApples(Inventory.getInstance(), new AppleGreenColorPredicate());
        System.out.println(greenApples.toString());
        List<Apple> heavyApples = filterApples(Inventory.getInstance(), new AppleHeavyWeightPredicate());
        System.out.println(heavyApples.toString());

        // 익명 함수
        List<Apple> redApples = filterApples(Inventory.getInstance(), new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        });
        System.out.println(redApples.toString());

        // 람다
        // ApplePredicate 타입이 아니지만 형식 추론(1개 입력, boolean return)으로 정상 동작한다.
        List<Apple> lightApple = filterApples(Inventory.getInstance(), (Apple apple) -> apple.getWight() < 150);
        System.out.println(lightApple.toString());

        // 리스트 형식으로 추상화
        List<Apple> yellowApples = filter(Inventory.getInstance(), (Apple apple) -> Color.YELLOW.equals(apple.getColor()));
        System.out.println(yellowApples.toString());
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if (p.test(apple)) {
               result.add(apple);
            }
        }
        return result;
    }

    // 리스트 형식으로 추상화
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T e : list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
