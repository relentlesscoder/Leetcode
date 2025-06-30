package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #2964 https://leetcode.com/problems/number-of-divisible-triplet-sums/
 */
public class NumberOfDivisibleTripletSums {

    // time O(n^2), space O(d)
    public int divisibleTripletCount(int[] nums, int d) {
        int res = 0, n = nums.length;
        int[] map = new int[d]; // use array since the map key is between 0 and d - 1
        for (int i = 2; i < n; i++) {
            int key = (d - nums[i] % d) % d;
            for (int j = i - 2; j >= 0; j--) {
                // For each i, choose i - 1 as fixed index and iterate nums
                // between 0 and i - 2 find the count of sum % d. Notice
                // that already calculated values in the map can be reused
                // for next i.
                int mod = (nums[i - 1] + nums[j]) % d;
                map[mod]++;
            }
            res += map[key];
        }
        return res;
    }
}
