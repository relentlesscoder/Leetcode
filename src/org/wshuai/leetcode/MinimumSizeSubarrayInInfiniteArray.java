package org.wshuai.leetcode;

/**
 * Created by Wei on 12/01/2025.
 * #2875 https://leetcode.com/problems/minimum-size-subarray-in-infinite-array/
 */
public class MinimumSizeSubarrayInInfiniteArray {

    // time O(n), space O(1)
    public int minSizeSubarray(int[] nums, int target) {
        int res = Integer.MAX_VALUE, n = nums.length;
        long sum = 0, total = 0;
        for (int num : nums) {
            total += num;
        }
        long x = target % total;
        int m = (int) (target / total);
        for (int i = 0, j = 0; i < 2 * n; i++) {
            sum += nums[i % n];
            while (sum > x) {
                sum -= nums[j % n];
                j++;
            }
            if (sum == x) {
                res = Math.min(res, i - j + 1);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res + m * n;
    }
}
