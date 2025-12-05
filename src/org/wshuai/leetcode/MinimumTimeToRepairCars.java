package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 07/13/2025.
 * #2594 https://leetcode.com/problems/minimum-time-to-repair-cars/
 */
public class MinimumTimeToRepairCars {

    // time O(n + r * log(r * m^2)), space O(r)
    public long repairCars(int[] ranks, int cars) {
        int[] freq = new int[101];
        int minRank = 101, maxRank = 0;
        for (int rank: ranks) {
            minRank = Math.min(minRank, rank);
            maxRank = Math.max(maxRank, rank);
            freq[rank]++;
        }
        long low = 1, high = 1L * maxRank * cars * cars;
        while (low < high) {
            long mid = low + (high - low) / 2, carsRepaired = 0;
            for (int rank = 1; rank < 101; rank++) {
                carsRepaired += freq[rank] * (long) Math.sqrt(mid / rank);
            }
            if (carsRepaired >= cars) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // time O(n + m * log(r)), space O(r)
    public long repairCarsMinQueue(int[] ranks, int cars) {
        int[] freq = new int[101];
        for (int rank: ranks) {
            freq[rank]++;
        }
        PriorityQueue<long[]> minHeap =
                new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        for (int i = 1; i < 101; i++) {
            if (freq[i] > 0) {
                // Elements: [time, rank, cars_repaired, mechanic_count]
                minHeap.offer(new long[] {i, i, 1, freq[i]});
            }
        }
        long time = 0;
        while (cars > 0) {
            long[] curr = minHeap.poll();
            time  = curr[0];
            long carsRepaired = curr[2];
            int rank = (int) curr[1], mechanicCount = (int) curr[3];
            carsRepaired += 1;
            cars -= mechanicCount;
            minHeap.offer(new long[] {
                    rank * carsRepaired * carsRepaired, rank, carsRepaired, mechanicCount });
        }
        return time;
    }
}
