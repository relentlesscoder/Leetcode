package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/17/2023.
 * #2398 https://leetcode.com/problems/maximum-number-of-robots-within-budget/
 */
public class MaximumNumberOfRobotsWithinBudget {

    // time O(n), space O(n)
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int res = 0, n = chargeTimes.length;
        long sum = 0L;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            while (!queue.isEmpty() && chargeTimes[queue.peekLast()] <= chargeTimes[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            sum += runningCosts[i];
            while (!queue.isEmpty() && sum * (i - j + 1) + chargeTimes[queue.peekFirst()] > budget) {
                if (queue.peekFirst() == j) {
                    queue.pollFirst();
                }
                sum -= runningCosts[j++];
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int maximumRobotsBinarySearch(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length, low = 0, high = n;
        long[] prefixSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + runningCosts[i - 1];
        }
        while (low < high) {
            int mid = (low + high + 1) >> 1;
            if (minCost(slidingWindowMax(chargeTimes, mid), prefixSum, mid) <= budget) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    // time O(n), space O(k)
    private long[] slidingWindowMax(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int n = nums.length;
        long[] res = new long[n - k + 1];
        for (int i = 0, j = 0; i < n; i++) {
            if (!queue.isEmpty() && i - queue.peekFirst() >= k) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            if (i >= k - 1) {
                res[j++] = nums[queue.peekFirst()];
            }
        }
        return res;
    }

    // time O(k), space O(1)
    private long minCost(long[] maxChargeTimes, long[] prefixSumCosts, int k) {
        long minCost = Long.MAX_VALUE;
        for (int i = 0, j = k; i < maxChargeTimes.length; i++, j++) {
            long sum = prefixSumCosts[j] - prefixSumCosts[j - k];
            minCost = Math.min(minCost, maxChargeTimes[i] + sum * k);
        }
        return minCost;
    }
}
