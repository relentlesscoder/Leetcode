package org.wshuai.leetcode.heap;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 05/03/2020.
 * #1438
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    // time O(n), space O(n)
    public int longestSubarray(int[] nums, int limit) {
        // 核心思路: 滑动窗口 + 单调双端队列
        // 窗口 [j, i] 内 max - min <= limit 时合法, 用两个单调队列分别维护窗口的最大值和最小值
        int res = 0, n = nums.length;
        // maxQueue: 单调递减队列, 队首是窗口最大值的下标
        Deque<Integer> maxQueue = new ArrayDeque<>();
        // minQueue: 单调递增队列, 队首是窗口最小值的下标
        Deque<Integer> minQueue = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            // 维护 maxQueue 单调递减: 弹出队尾所有 <= nums[i] 的元素
            while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] <= nums[i]) {
                maxQueue.pollLast();
            }
            maxQueue.offer(i);
            // 维护 minQueue 单调递增: 弹出队尾所有 >= nums[i] 的元素
            while (!minQueue.isEmpty() && nums[minQueue.peekLast()] >= nums[i]) {
                minQueue.pollLast();
            }
            minQueue.offer(i);
            // 窗口不合法 (max - min > limit), 收缩左边界 j
            // 不需要检查队列是否为空: i 已在两个队列中, 且循环只弹 == j 的队首 (j < i)
            // j 最多追到 i, 此时窗口只剩 nums[i], max - min = 0 <= limit, 循环终止
            while (nums[maxQueue.peek()] - nums[minQueue.peek()] > limit) {
                // 如果队首元素是即将被移出窗口的 j, 弹出队首
                if (maxQueue.peek() == j) {
                    maxQueue.poll();
                }
                if (minQueue.peek() == j) {
                    minQueue.poll();
                }
                j++;
            }
            // 窗口 [j, i] 合法, 更新最长长度
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
