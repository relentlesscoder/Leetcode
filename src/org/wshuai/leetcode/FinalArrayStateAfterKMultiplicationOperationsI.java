package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 10/01/2025.
 * #3264 https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/
 */
public class FinalArrayStateAfterKMultiplicationOperationsI {

    // time O((n + k) * log(n)), space O(n)
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) ->
                a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < nums.length; i++) {
            minQueue.offer(new int[]{nums[i], i});
        }
        while (k-- > 0) {
            int[] curr = minQueue.poll();
            curr[0] *= multiplier;
            minQueue.offer(curr);
            nums[curr[1]] = curr[0];
        }
        return nums;
    }
}
