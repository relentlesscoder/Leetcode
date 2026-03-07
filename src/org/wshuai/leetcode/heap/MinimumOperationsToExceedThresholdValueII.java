package org.wshuai.leetcode.heap;

import java.util.PriorityQueue;

/**
 * Created by Wei on 08/04/2025.
 * #3066 https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/
 */
public class MinimumOperationsToExceedThresholdValueII {

    // time O(n * log(n)), space O(n)
    public int minOperations(int[] nums, int k) {
        int res = 0;
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for (int num : nums) {
            if (num < k) {
                minQueue.offer(num);
            }
        }
        while (!minQueue.isEmpty()) {
            // 注意最后只剩一个数的情况，操作数也需要加一。
            res++;
            int x = minQueue.poll();
            if (minQueue.isEmpty()) {
                break;
            }
            int y = minQueue.poll();
            long v = 2L * x + y;
            // 只有当 v < k 才加入
            if (v < k) {
                minQueue.offer((int) v);
            }
        }
        return res;
    }
}
