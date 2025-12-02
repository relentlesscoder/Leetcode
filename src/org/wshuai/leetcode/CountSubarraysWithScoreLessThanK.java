package org.wshuai.leetcode;

/**
 * Created by Wei on 02/04/2024.
 * #2302 https://leetcode.com/problems/count-subarrays-with-score-less-than-k/
 */
public class CountSubarraysWithScoreLessThanK {

    // time O(n), space O(1)
    public long countSubarrays(int[] nums, long k) {
        long res = 0, sum = 0;
        int n = nums.length;
        for (int i = 0, j = 0; i < n; i++) {
            sum += nums[i];
            // Both sum and size (i - j + 1) are decreased when we
            // advance index j so this is monotonic - all index j'
            // in [j,i] can form a valid subarray with i, so we can
            // add i - j + 1 to the result.
            while (sum * (i - j + 1) >= k) {
                sum -= nums[j++];
            }
            res += i - j + 1;
        }
        return res;
    }
}
