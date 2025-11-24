package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/12/2023.
 * #2289 https://leetcode.com/problems/steps-to-make-array-non-decreasing/
 */
public class StepsToMakeArrayNonDecreasing {

    // time O(n), space O(n)
    public int totalSteps(int[] nums) {
        // A number nums[i] only needs to be deleted if there is a strictly
        // greater number nums[j] on its left. The step it will be deleted
        // is determined by the distance between index i and j.
        // e.g. [5,3,4,4,7,3,6,11,8,5,11]
        //   index 1 (3) will be deleted at 1st round - (1 - 0)
        //   index 2 (4) will be deleted at 2nd round - (2 - 0)
        //   index 3 (4) will be deleted at 2nd round - (3 - 0) ...
        // Until we found index 4 (7) which is greater than index 0 (5) then
        // we need to reset the step to 0.
        int res = 0, n = nums.length;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[] {-1, -1}); // Sentinel
        for (int i = 0; i < n; i++) {
            int steps = 0;
            while (stack.size() > 1 && nums[stack.peek()[0]] <= nums[i]) {
                // We need to get max steps for all popped out indexes
                //   e.g. for input [7,14,4,14,13,2,6,13]
                //   the steps:     [0, 0,1, 0, 1,1,2, 3]
                // For index 7 (13), it will be deleted at 3rd step, one step
                // after (+1) the max of index 4, 5, and 6.
                steps = Math.max(steps, stack.pop()[1]);
            }
            // If stack is empty, meaning nums[i] is the current max (no number
            // on its left that is greater) then we reset the step to 0.
            int[] curr = new int[] {i, stack.size() == 1 ? 0 : steps + 1};
            stack.push(curr);
            res = Math.max(res, curr[1]);
        }
        return res;
    }
}
