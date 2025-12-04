package org.wshuai.leetcode;

/**
 * Created by Wei on 01/01/2024.
 * #2422 https://leetcode.com/problems/merge-operations-to-turn-array-into-a-palindrome/
 */
public class MergeOperationsToTurnArrayIntoAPalindrome {

    // time O(n), space O(1)
    public int minimumOperations(int[] nums) {
        int res = 0, n = nums.length;
        long leftSum = nums[0], rightSum = nums[n - 1];
        for (int i = 0, j = n - 1; i < j; ) {
            if (leftSum == rightSum) {
                leftSum = nums[++i];
                rightSum = nums[--j];
            } else if (leftSum > rightSum) {
                rightSum += nums[--j];
                res++;
            } else {
                leftSum += nums[++i];
                res++;
            }
        }
        return res;
    }
}
