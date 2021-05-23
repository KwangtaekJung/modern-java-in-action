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
        // List.of: 고정 요소의 리스트 생성
        List<String> friendsByListOf = List.of("Raphael", "Olivia", "Thibaut");
//        friendsByListOf.add("Chih-Chun");  // UnsupportedOperationException

        System.out.println("------ Working with Lists ------");
        System.out.println("--> Transforming list items with a Stream");

    }
}
