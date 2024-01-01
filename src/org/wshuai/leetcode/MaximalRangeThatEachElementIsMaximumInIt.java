package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 01/01/2024.
 * #2832 https://leetcode.com/problems/maximal-range-that-each-element-is-maximum-in-it/
 */
public class MaximalRangeThatEachElementIsMaximumInIt {

    // time O(n), space O(n)
    public int[] maximumLengthOfRanges(int[] nums) {
        int n = nums.length;
        int[] res = new int[n], left = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) { // use monotonic queue to find the first element on the left with value grater than the current
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) { // use monotonic queue to find the first element on the right with value grater than the current
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            int right = stack.isEmpty() ? n : stack.peek();
            res[i] = right - left[i] - 1;
            stack.push(i);
        }
        return res;
    }
}
