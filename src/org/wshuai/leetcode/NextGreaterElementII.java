package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 02/26/2017.
 * #0503 https://leetcode.com/problems/next-greater-element-ii/
 */
public class NextGreaterElementII {

    // time O(n), space O(n)
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < 2 * n; i++) {
            while (stack.size() > 1 && nums[stack.peek()] < nums[i % n]) {
                res[stack.pop()] = nums[i % n];
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return res;
    }
}
