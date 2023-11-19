package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2023.
 * #2498 https://leetcode.com/problems/frog-jump-ii/
 */
public class FrogJumpII {

    // time O(n), space O(1)
    public int maxJump(int[] stones) {
        // For array with index [0 1 2 3 4 5 6 7],
        // we will calculate the path forward and backward [0 2 4 6]
        // and [0 1 3 5 7]. We can see cost 6 -> 7 is not calculated,
        // but it will not affect the result since the first jump 7 -> 5
        // of the path backward will always greater than it.
        int n = stones.length, res = stones[1] - stones[0];
        for (int i = 3; i < n; i += 2) {
            res = Math.max(res, stones[i] - stones[i - 2]);
        }
        for (int j = 2; j < n; j += 2) {
            res = Math.max(res, stones[j] - stones[j - 2]);
        }
        return res;
    }
}
