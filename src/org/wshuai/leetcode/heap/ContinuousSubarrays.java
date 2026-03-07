package org.wshuai.leetcode.heap;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/12/2023.
 * #2762 https://leetcode.com/problems/continuous-subarrays/
 */
public class ContinuousSubarrays {

    // time O(n), space O(n)
    public long continuousSubarrays(int[] nums) {
        // 同#1438
        long res = 0;
        int n = nums.length;
        Deque<Integer> maxQueue = new ArrayDeque<>(), minQueue = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] <= nums[i]) {
                maxQueue.pollLast();
            }
            while (!minQueue.isEmpty() && nums[minQueue.peekLast()] >= nums[i]) {
                minQueue.pollLast();
            }
            maxQueue.offer(i);
            minQueue.offer(i);
            while (nums[maxQueue.peek()] - nums[minQueue.peek()] > 2) {
                if (maxQueue.peek() == j) {
                    maxQueue.poll();
                }
                if (minQueue.peek() == j) {
                    minQueue.poll();
                }
                j++;
            }
            res += i - j + 1;
        }
        return res;
    }
}
