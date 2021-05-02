package ch5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Filtering {
    public static void main(String[] args) {

        // 5.1.1 Predicate로 필터링
        System.out.println("Filtering with a Predicate");
        List<Dish> vegetarianMenu = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        vegetarianMenu.forEach(System.out::println);

        // 5.1.2 고유 요소 필터링
        System.out.println("Filtering with unique elements");
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        // 5.2 스트림 슬라이싱
        // 5.2.1 Predicate를 이용한 슬라이싱
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));
        System.out.println("Filtered sorted menu:");
        List<Dish> filteredMenu = specialMenu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
        filteredMenu.forEach(System.out::println);

        System.out.println("Sorted menu sliced with takewhile():");
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
        slicedMenu1.forEach(System.out::println);

        System.out.println("Soreted menu sliced with dropwhile():");
        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
        slicedMenu2.forEach(System.out::println);

        // 5.3 매핑
        // 5.3.2 스트림 평면화
        String[] words = {"Goodbye", "World"};
        List<String[]> collect = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        List<String[]> collect1 = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        collect1.forEach(System.out::println);
        List<String> collect2 = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        collect2.forEach(System.out::println);

        // 5.4 검색과 매칭
        // 5.4.1 프레디케이트가 적어도 한 요소와 일치하는지 확인
        if(Dish.menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (something) vegetarian friendly.");
        }

        // 5.4.3 요서 검색
        Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println(dish.getName()));

        // 5.4.4 첫번째 요소 찾기
        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> first = someNumbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst();
        first.ifPresent(System.out::println);
    }
}
