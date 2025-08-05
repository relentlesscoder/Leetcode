package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 08/04/2025.
 * #3066 https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/
 */
public class MinimumOperationsToExceedThresholdValueII {

    // time O(n * log(n)), space O(n)
    public int minOperationsOptimized(int[] nums, int k) {
        int res = 0;
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for (int num : nums) {
            if (num < k) {
                minQueue.offer(num);
            }
        }
        while (!minQueue.isEmpty()) {
            res++;
            int first = minQueue.poll();
            // if there is only one in the queue, it means all other values are >= k
            // then we only need one more operation
            if (minQueue.isEmpty()) {
                break;
            }
            int second = minQueue.poll();
            long value = 2L * first + second;

            // only add back to queue if the increased value is still < k
            if (value < k) {
                minQueue.offer((int)value);
            }
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int minOperations(int[] nums, int k) {
        int res = 0;
        PriorityQueue<Long> minQueue = new PriorityQueue<>();
        for (int num : nums) {
            minQueue.offer(1L * num);
        }
        while (minQueue.peek() < k) {
            long first = minQueue.poll(), second = minQueue.poll();
            minQueue.offer((first << 1) + second);
            res++;
        }
        return res;
    }
}
