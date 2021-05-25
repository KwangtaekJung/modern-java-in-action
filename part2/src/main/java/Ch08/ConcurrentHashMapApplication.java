package Ch08;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapApplication {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
//        long parallelismThreshold = 1;
//        Optional<Long> maxValue = Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));
//        maxValue.ifPresent(System.out::println);

        int MAP_SIZE = 2;
        Map<String, Integer>[] maps = new Map[MAP_SIZE];
        maps[0] = Collections.synchronizedMap(new HashMap<>());
        maps[1] = new ConcurrentHashMap<>();
        List<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            String uid = UUID.randomUUID().toString();
            arrayList.add(uid);
            int val = (int) ((Math.random() * Integer.MAX_VALUE) + 1);
            for (int j = 0; j < MAP_SIZE; j++) {
                maps[j].put(uid, val);
            }
        }

        System.out.println("============= containsKey time in multi thread =============");
        AtomicInteger[] atomicIntegers = new AtomicInteger[MAP_SIZE];
        atomicIntegers[0] = new AtomicInteger(0);
        atomicIntegers[1] = new AtomicInteger(0);
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(availableProcessors);
        Future<?>[] future = new Future[availableProcessors];
        //쓰레드 풀 개수(프로세서 수) 만큼 반복, 모든 쓰레드에 작업 할당
        for (int i = 0; i < availableProcessors; i++) {
            //쓰레드 풀을 이용해 멀티 쓰레드로 Map의 get 메소드 호출
            future[i] = service.submit(() -> {
                for (int j = 0; j < MAP_SIZE; j++) {
                    long st = System.currentTimeMillis();
                    for (int k = 0; k < arrayList.size(); k++) {
                        maps[j].get(arrayList.get(k));
                    }
                    //쓰레드 별 걸린 작업시간 측정 및 추가
                    atomicIntegers[j].addAndGet((int) (System.currentTimeMillis() - st));
                }
            });
        }
        //결과값 대기
        for (int i = 0; i < availableProcessors; i++) {
            future[i].get();
        }
        for (int i = 0; i < MAP_SIZE; i++) {
            //Map 종류별로 걸린 평균시간 출력
            System.out.println(maps[i].getClass().toString() + " " + atomicIntegers[i].get() / availableProcessors);
        }

    }
}
