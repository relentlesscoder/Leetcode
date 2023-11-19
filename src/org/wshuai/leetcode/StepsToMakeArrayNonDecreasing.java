package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/12/2023.
 * #2289 https://leetcode.com/problems/steps-to-make-array-non-decreasing/
 */
public class StepsToMakeArrayNonDecreasing {

    // time O(n), space O(n)
    public int totalSteps(int[] nums) {
        int res = 0;
        Stack<int[]> stack = new Stack<>();
        // use monotonic stack to find subarrays like 5,3,4,4,7
        // which needs 3 steps to make it non-decreasing
        for (int i = 0; i < nums.length; i++) {
            int score = 0;
            while (!stack.isEmpty() && stack.peek()[0] <= nums[i]) {
                score = Math.max(score, stack.pop()[1]);
            }
            // stack is empty mean the left boundary is removed thus we need to reset the score
            stack.push(new int[] {nums[i], stack.isEmpty() ? 0 : score + 1});
            res = Math.max(res, stack.peek()[1]);
        }
        return res;
    }
}
