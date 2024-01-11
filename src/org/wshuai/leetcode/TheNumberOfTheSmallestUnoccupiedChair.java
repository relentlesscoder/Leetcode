package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 01/10/2024.
 * #1942 https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/
 */
public class TheNumberOfTheSmallestUnoccupiedChair {

    // time O(n * log(n)), space O(n)
    public int smallestChair(int[][] times, int targetFriend) {
        int index = 0, n = times.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(times[i][0], i);
        }
        PriorityQueue<int[]> reserved = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> available = new PriorityQueue<Integer>();
        Arrays.sort(times, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            while (!reserved.isEmpty() && reserved.peek()[0] <= times[i][0]) {
                available.offer(reserved.poll()[1]);
            }
            int assigned = available.isEmpty() ? index++ : available.poll();
            reserved.offer(new int[]{times[i][1], assigned});
            if (map.get(times[i][0]) == targetFriend) {
                return assigned;
            }
        }
        return -1;
    }
}
