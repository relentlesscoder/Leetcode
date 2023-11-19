package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/06/2023.
 * #2104 https://leetcode.com/problems/sum-of-subarray-ranges/
 */
public class SumOfSubarrayRanges {

    // time O(n), space O(n)
    public long subArrayRanges(int[] nums) {
        long res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int r = 0; r <= nums.length; r++) {
            while (!stack.isEmpty() && (r == nums.length || nums[stack.peek()] >= nums[r])) {
                int m = stack.pop(), l = stack.isEmpty() ? -1 : stack.peek();
                res -= (long)nums[m] * (m - l) * (r - m);
            }
            stack.push(r);
        }
        stack.clear();
        for (int r = 0; r <= nums.length; r++) {
            while (!stack.isEmpty() && (r == nums.length || nums[stack.peek()] <= nums[r])) {
                int m = stack.pop(), l = stack.isEmpty() ? -1 : stack.peek();
                res += (long)nums[m] * (m - l) * (r - m);
            }
            stack.push(r);
        }
        return res;
    }
}
