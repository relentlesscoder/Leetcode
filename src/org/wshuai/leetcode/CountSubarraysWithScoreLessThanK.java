package org.wshuai.leetcode;

/**
 * Created by Wei on 02/04/2024.
 * #2302 https://leetcode.com/problems/count-subarrays-with-score-less-than-k/
 */
public class CountSubarraysWithScoreLessThanK {

    // time O(n), space O(1)
    public long countSubarrays(int[] nums, long k) {
        long res = 0, sum = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum * (j - i + 1) >= k) {
                sum -= nums[i++];
            }
            res += (j - i + 1); // if subarray [i, j] if valid, then all [k, j] is also valid where k >= i and k <= j
        }
        return res;
    }
}
