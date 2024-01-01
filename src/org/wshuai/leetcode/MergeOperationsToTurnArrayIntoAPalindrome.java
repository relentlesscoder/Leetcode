package org.wshuai.leetcode;

/**
 * Created by Wei on 01/01/2024.
 * #2422 https://leetcode.com/problems/merge-operations-to-turn-array-into-a-palindrome/
 */
public class MergeOperationsToTurnArrayIntoAPalindrome {

    // time O(n), space O(1)
    public int minimumOperations(int[] nums) {
        int res = 0, n = nums.length, i = 0, j = n - 1, left = nums[0], right = nums[n - 1];
        while (i < j) {
            if (left == right) {
                left = nums[++i];
                right = nums[--j];
            } else if (left < right) {
                left += nums[++i];
                res++;
            } else {
                right += nums[--j];
                res++;
            }
        }
        return res;
    }
}
