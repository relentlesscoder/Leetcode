package org.wshuai.leetcode;

/**
 * Created by Wei on 06/22/2025.
 * #3375 https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/
 */
public class MinimumOperationsToMakeArrayValuesEqualToK {

    // time O(n), space O(1)
    public int minOperations(int[] nums, int k) {
        int res = 0;
        int[] freq = new int[101];
        for (int num : nums) {
            if (num == k) { // no operation needed
                continue;
            }
            if (num < k) { // impossible if num less than k exists
                return -1;
            }
            // requires one more operation for any new num that are greater than k
            if(freq[num]++ == 0) {
                res++;
            }
        }
        return res;
    }
}
