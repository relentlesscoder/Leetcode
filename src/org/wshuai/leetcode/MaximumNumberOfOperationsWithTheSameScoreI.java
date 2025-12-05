package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3038 https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-i/
 */
public class MaximumNumberOfOperationsWithTheSameScoreI {

    // time O(n), space O(1)
    public int maxOperations(int[] nums) {
        int res = 1, score = nums[0] + nums[1];
        for (int i = 2; i < nums.length; i += 2) {
            if (i + 1 >= nums.length || nums[i] + nums[i + 1] != score) {
                break;
            }
            res++;
        }
        return res;
    }
}
