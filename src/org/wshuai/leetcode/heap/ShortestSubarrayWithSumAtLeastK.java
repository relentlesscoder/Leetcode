package org.wshuai.leetcode.heap;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/18/2019.
 * #0862 https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 */
public class ShortestSubarrayWithSumAtLeastK {

    // time O(n), space O(n)
    public int shortestSubarray(int[] nums, int k) {
		// https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/solutions/1925036/liang-zhang-tu-miao-dong-dan-diao-dui-li-9fvh/
        int n = nums.length, res = n + 1;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            while (!queue.isEmpty() && prefix[i] - prefix[queue.peek()] >= k) {
                res = Math.min(res, i - queue.poll());
            }
            while (!queue.isEmpty() && prefix[queue.peekLast()] >= prefix[i]) {
                queue.pollLast();
            }
            queue.offer(i);
        }
        return res > n ? -1 : res;
    }
}
