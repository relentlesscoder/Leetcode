package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/2023.
 * #2176 https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/
 */
public class CountEqualAndDivisiblePairsInAnArray {

    // time O(n ^ 2), space O(1)
    public int countPairs(int[] nums, int k) {
        int res = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] != nums[j] || (i * j) % k != 0) {
                    continue;
                }
                res++;
            }
        }
        return res;
    }
}
