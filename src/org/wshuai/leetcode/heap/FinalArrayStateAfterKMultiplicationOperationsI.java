package org.wshuai.leetcode.heap;

import java.util.PriorityQueue;

/**
 * Created by Wei on 10/01/2025.
 * #3264 https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/
 */
public class FinalArrayStateAfterKMultiplicationOperationsI {

    // time O((n + k) * log(n)), space O(n)
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) ->
                a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < nums.length; i++) {
            queue.offer(new int[]{i, nums[i]});
        }
        while (k-- > 0) {
            int[] curr = queue.poll();
            curr[1] *= multiplier;
            nums[curr[0]] = curr[1];
            queue.offer(curr);
        }
        return nums;
    }
}
