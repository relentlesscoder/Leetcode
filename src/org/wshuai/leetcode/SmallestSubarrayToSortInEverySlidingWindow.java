package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/25/2025.
 * #3555 https://leetcode.com/problems/smallest-subarray-to-sort-in-every-sliding-window/
 */
public class SmallestSubarrayToSortInEverySlidingWindow {

    // time O(n * k), space O(1)
    public int[] minSubarraySort(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        // 对每个滑动窗口复用#0581代码找到最短的无序子数组
        for (int i = k - 1; i < n; i++) {
            res[i - k + 1] = findUnsortedSubarray(nums, i - k + 1, i);
            //res[i - k + 1] = findUnsortedSubarrayMonotonicStack(nums, i - k + 1, i);
        }
        return res;
    }

    private int findUnsortedSubarray(int[] nums, int left, int right) {
        int start = left, end = left - 1,
                max = nums[left], min = nums[right];
        for (int i = left; i <= right; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else { // nums[i] < max
                end = i;
            }
        }
        for (int i = right; i >= left; i--) {
            if (nums[i] <= min) {
                min = nums[i];
            } else { // nums[i] > min
                start = i;
            }
        }
        return end - start + 1;
    }

    private int findUnsortedSubarrayMonotonicStack(int[] nums, int left, int right) {
        int start = right + 1, end = left - 1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = left; i <= right; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                start = Math.min(start, stack.pop());
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = right; i >= left; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                end = Math.max(end, stack.pop());
            }
            stack.push(i);
        }
        return Math.max(0, end - start + 1);
    }
}
