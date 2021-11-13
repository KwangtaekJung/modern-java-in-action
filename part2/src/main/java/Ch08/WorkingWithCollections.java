package Ch08;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkingWithCollections {
    public static void main(String[] args) {
        // 8.1 컬렉션 팩토리

        // 작은 길이의 리스트 만들기
        // 방법1) new ArrayList: 길고 불편한 방법
        List<String> friendsOld = new ArrayList<>();
        friendsOld.add("Raphael");
        friendsOld.add("Olivai");
        friendsOld.add("Thibaut");
        // 방법2) asList: 간단하지만 고정 길이라서 추가 불가능
        List<String> friends = Arrays.asList("Raphael", "Olivia", "Thibaut");
        System.out.println(friends);
        friends.set(0, "Richard");
//        friends.add("Thibaut");  // UnsupportedOperationException

        // 작은 길이의 맵 만들기 by new HashSet
        Set<String> friendsMap = new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));
        // 작은 길이의 맵 만들기 by Stream
        Set<String> friendsByStream = Stream.of("Rahpael", "Olivia", "Thibaut").collect(Collectors.toSet());

        // 8.1.1 리스트 팩토리
        // List.of: 고정 및 불변 요소의 리스트 생성
        List<String> friendsByListOf = List.of("Raphael", "Olivia", "Thibaut");
//        friendsByListOf.add("Chih-Chun");  // UnsupportedOperationException

        // 8.1.2 집합 팩토리
        // Set.of
        Set<String> friendsBySetOf = Set.of("Raphael", "Olivia", "Thibaut");

        // 8.1.3 맵 팩토리
        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        System.out.println(ageOfFriends);

        Map<String, Integer> ageOfFriendsByOfEntries = Map.ofEntries(
                Map.entry("Raphael", 30),
                Map.entry("Olivia", 25),
                Map.entry("Thibaut", 26));
        System.out.println(ageOfFriendsByOfEntries);

        // 8.3 맵 처리
        // 8.3.1 forEach 메서드
        for(Map.Entry<String, Integer> entry : ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + " is " + age + " years old");
        }

        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old"));

        // 8.3.2 정렬 메서드
        Map<String, String> favoriteMovies = Map.ofEntries(Map.entry("Raphael", "Stat Wars"),
                Map.entry("Cristina", "Matrix"),
                Map.entry("Olivia", "James Bond"));

        favoriteMovies
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        // 8.3.3 getOrDefault Method
        System.out.println(favoriteMovies.getOrDefault("Olivia", "Matrix2"));
        System.out.println(favoriteMovies.getOrDefault("Thibaut", "Matrix2"));

        // 8.3.4 계산 패턴
        Map<String, List<String>> friendsToMovies = new HashMap<>();
        String friend = "Raphael";
        List<String> movies = friendsToMovies.get(friend);
        if(movies == null) {
            movies = new ArrayList<>();
            friendsToMovies.put(friend, movies);
        }
        movies.add("Star Wars");
        System.out.println(friendsToMovies);

        friendsToMovies.computeIfAbsent("Thibaut", name -> new ArrayList<>())
                .add("James Bond");
        System.out.println(friendsToMovies);

        // 8.3.5
        System.out.println("------ Working with Lists ------");
        System.out.println("--> Transforming list items with a Stream");

    }
}
