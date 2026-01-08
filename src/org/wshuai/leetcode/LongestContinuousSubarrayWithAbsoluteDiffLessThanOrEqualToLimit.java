package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 05/03/2020.
 * #1438 https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    // time O(n), space O(n)
    public int longestSubarray(int[] nums, int limit) {
        // 维护两个单调队列分别存当前窗口的最大值和最小值
        int res = 0, n = nums.length;
        Deque<Integer> maxQueue = new ArrayDeque<>(), minQueue = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            while (!maxQueue.isEmpty() && nums[i] >= nums[maxQueue.peekLast()]) {
                maxQueue.pollLast();
            }
            while (!minQueue.isEmpty() && nums[i] <= nums[minQueue.peekLast()]) {
                minQueue.pollLast();
            }
            maxQueue.offer(i);
            minQueue.offer(i);
            // 收缩左端点直到找到一个合法的窗口
            while (nums[maxQueue.peek()] - nums[minQueue.peek()] > limit) {
                if (minQueue.peek() == j) {
                    minQueue.poll();
                }
                if (maxQueue.peek() == j) {
                    maxQueue.poll();
                }
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
