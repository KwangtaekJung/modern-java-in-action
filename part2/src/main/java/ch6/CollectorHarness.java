package ch6;

import static ch6.PartitionPrimeNumbers.partitionPrimes;
import static ch6.PartitionPrimeNumbers.partitionPrimesWithCustomCollector;

public class CollectorHarness {
    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(1_000_000);
            long duration = (System.nanoTime() - start);
            if (duration < fastest) fastest = duration;
        }

        System.out.println("[partitionPrimes] Fastest execution done in " + fastest + "ms");

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1_000_000);
            long duration = (System.nanoTime() - start);
            if (duration < fastest) fastest = duration;
        }

        System.out.println("[partitionPrimesWithCustomCollector] Fastest execution done in " + fastest + "ms");
    }
}
