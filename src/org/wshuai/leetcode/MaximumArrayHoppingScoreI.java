package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/24/2025.
 * #3205 https://leetcode.com/problems/maximum-array-hopping-score-i/
 */
public class MaximumArrayHoppingScoreI {

    // time O(n), space O(n)
    public int maxScore(int[] nums) {
        int res = 0, n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int end = stack.pop();
            int start = stack.isEmpty() ? 0 : stack.peek();
            res += (end - start) * nums[end];
        }
        return res;
    }
}
