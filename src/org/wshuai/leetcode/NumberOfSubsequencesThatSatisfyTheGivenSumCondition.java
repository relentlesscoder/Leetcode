package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/25/2020.
 * #1498 https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
 */
public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition {

    private static final int MOD = (int) 1e9 + 7;
    private static final int MAX = (int) 1e5;
    private static final int[] POW = new int[MAX];

    static {
        POW[0] = 1;
        for (int i = 1; i < MAX; i++) {
            POW[i] = POW[i - 1] * 2 % MOD;
        }
    }

	// time O(n * log(n)), space O(log(n))
    public int numSubseq(int[] nums, int target) {
        long res = 0;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0, j = n - 1; i <= j; ) {
            if (nums[i] > target) {
                break;
            }
            // If nums[i] + nums[j] <= target, then all subsequences
            // in [i, j] with nums[i] as the min are valid, the number
            // of these subsequences is 2^(j - i)
            if (nums[i] + nums[j] <= target) {
                res = res + POW[j - i];
                i++;
            } else {
                j--;
            }
        }
        return (int) (res % MOD);
    }
}
