package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/20/2020.
 * #1589 https://leetcode.com/problems/maximum-sum-obtained-of-any-permutation/
 */
public class MaximumSumObtainedOfAnyPermutation {

    private int MOD = 1_000_000_007;

    // time O(n*log(n)), space O(n)
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        long[] count = new long[n];
        // running sum
        for(int[] r : requests){
            count[r[0]] += 1;
            if(r[1] + 1 < n){
                count[r[1] + 1] -= 1;
            }
        }
        for(int i = 1; i < n; i++){
            count[i] += count[i - 1];
        }
        Arrays.sort(count);
        Arrays.sort(nums);
        long res = 0;
        for(int i = n - 1; i >= 0; i--){
            if(count[i] == 0){
                break;
            }
            res = (res + count[i] * nums[i] % MOD) % MOD;
        }
        return (int)res;
    }
}
