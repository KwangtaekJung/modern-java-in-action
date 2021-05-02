package ch5;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStream {
    public static void main(String[] args) {

        // 5.8 스트림 만들기
        //5.8.1 값으로 스트림 만들기
        // Stream.of
        Stream<String> stream = Stream.of("Modern ", "Java ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // Stream.empty
        Stream<String> emptyStream = Stream.empty();

        // Stream.ofNullable
        String homeValue = System.getProperty("GRADLE_HOME");
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        homeValueStream.forEach(System.out::println);

        Stream<String> homeValueStream2 = Stream.ofNullable(System.getProperty("home"));

        // 5.8.3 배열로 스트림 만들기
        int[] numbers = {2,3,5,7,11,13};
        int sum = Arrays.stream(numbers).sum();

        // 5.8.4 파일로 스트림 만들기

        // 5.8.5 함수로 무한 스트림 만들기
        Stream.iterate(0, n -> n +2)
                .limit(10)
                .forEach(System.out::println);

        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);
    }
}
