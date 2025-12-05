package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #2964 https://leetcode.com/problems/number-of-divisible-triplet-sums/
 */
public class NumberOfDivisibleTripletSums {

    // time O(n^2), space O(d)
    public int divisibleTripletCount(int[] nums, int d) {
        int res = 0, n = nums.length;
        int[] count = new int[d];
        for (int k = 2; k < n; k++) {
            int j = k - 1;
            for (int i = j - 1; i >= 0; i--) {
                count[(nums[i] + nums[j]) % d]++;
            }
            res += count[(d - nums[k] % d) % d];
        }
        return res;
    }
}
