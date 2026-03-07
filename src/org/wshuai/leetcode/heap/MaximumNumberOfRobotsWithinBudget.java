package org.wshuai.leetcode.heap;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/17/2023.
 * #2398 https://leetcode.com/problems/maximum-number-of-robots-within-budget/
 */
public class MaximumNumberOfRobotsWithinBudget {

    // time O(n), space O(n)
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        // 由于有预算的限制，右端点和左端点的移动具有单调性 - 可用滑动窗口。
        int res = 0, n = chargeTimes.length;
        long sum = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            sum += runningCosts[i];
            while (!queue.isEmpty() && chargeTimes[queue.peekLast()] <= chargeTimes[i]) {
                queue.pollLast();
            }
            queue.offer(i);
            // 收缩左端点如果当前的开销超过了预算
            while (!queue.isEmpty() && sum * (i - j + 1) + chargeTimes[queue.peek()] > budget) {
                if (queue.peek() == j) {
                    queue.poll();
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
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (canRun(chargeTimes, runningCosts, budget, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean canRun(int[] chargeTimes, int[] runningCosts, long budget, int k) {
        int n = chargeTimes.length;
        long sum = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            sum += runningCosts[i];
            while (!queue.isEmpty() && chargeTimes[queue.peekLast()] <= chargeTimes[i]) {
                queue.pollLast();
            }
            queue.offer(i);
            int left = i - k + 1;
            if (left < 0) {
                continue;
            }
            if (sum * k + chargeTimes[queue.peek()] <= budget) {
                return true;
            }
            sum -= runningCosts[left];
            if (queue.peek() == left) {
                queue.poll();
            }
        }
        return false;
    }
}
