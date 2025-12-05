package org.wshuai.leetcode;

/**
 * Created by Wei on 08/25/2025.
 * #3541 https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/
 */
public class FindMinimumOperationsToMakeAllElementsDivisibleByThree {

    // time O(n), space O(1)
    public int minimumOperations(int[] nums) {
        int res = 0;
        for (int num : nums) {
            if (num % 3 != 0) {
                res++;
            }
        }
        return res;
    }
}
