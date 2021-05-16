package ch6;

import ch5.Dish;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Grouping {

    enum CaloricLevel {DIET, NORMAL, FAT}

    ;

    public static void main(String[] args) {

        // 6.3 Grouping
        System.out.println("Dishes grouped by type: " + groupDishesByType());

        // 6.3.1 그룹화된 요소 조작
        System.out.println("Caloric dishes grouped by type: " + groupCaloricDishesByType());
        System.out.println("Dish names grouped by type: " + groupDishesNameByType());
        // 6.3.2 다수준 그룹화
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishesByTypeAndCaloricLevel());
        // 6.3.3 서브그룹으로 데이터 수집
        System.out.println("Count dishes in group: " + countDishesInGroup());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type with maxBy: " + mostCaloricDishesByTypeWithMaxBy());
        System.out.println("Most caloric dishes by type without Optional: " + mostCaloricDishesByTypeWithoutOptional());
        System.out.println("Caloric sum grouped by type: " + sumCaloricByTypeGroup());
        System.out.println("Caloric Level of group by type: " + caloricLevelGroupByType());

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

    private static Map<Dish.Type, List<String>> groupDishesNameByType() {
        Map<Dish.Type, List<String>> collect = Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping(Dish::getName, toList())));
        return collect;
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        Map<CaloricLevel, List<Dish>> collect = Dish.menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                })
        );
        return collect;
    }

     private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishesByTypeAndCaloricLevel() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy((Dish dish) -> {
                                    if (dish.getCalories() <= 400) {
                                        return CaloricLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return CaloricLevel.NORMAL;
                                    } else {
                                        return CaloricLevel.FAT;
                                    }
                                }
                        ))
        );
    }

    private static Map<Dish.Type, Long> countDishesInGroup() {
        return Dish.menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
        );
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByTypeWithMaxBy() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        maxBy(Comparator.comparingInt(Dish::getCalories)))
        );
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptional() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
//                                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        ))
        );
    }

    private static Map<Dish.Type, Integer> sumCaloricByTypeGroup() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        summingInt(Dish::getCalories))
        );
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelGroupByType() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping((Dish dish) -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        },
//                                toSet()))
                                toCollection(HashSet::new)))
        );
    }
}
