package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2019.
 * #0930 https://leetcode.com/problems/binary-subarrays-with-sum/
 */
public class BinarySubarraysWithSum {

    // time O(n), space O(1)
    public int numSubarraysWithSum3Pointers(int[] nums, int goal) {
        int res = 0, n = nums.length;
        for (int i = 0, j1 = 0, j2 = 0, sum1 = 0, sum2 = 0; i < n; i++) {
            sum1 += nums[i];
            while (j1 <= i && sum1 >= goal) {
                sum1 -= nums[j1++];
            }

            sum2 += nums[i];
            while (j2 <= i && sum2 >= goal + 1) {
                sum2 -= nums[j2++];
            }

            res += j1 - j2;
        }
        return res;
    }

    // time O(n), space O(1)
    public int numSubarraysWithSum(int[] nums, int goal) {
        return solve(nums, goal) - solve(nums, goal + 1);
    }

    private int solve(int[] nums, int goal) {
        int res = 0, n = nums.length;
        for (int i = 0, j = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            while (j <= i && sum >= goal) {
                sum -= nums[j++];
            }
            res += j;
        }
        return res;
    }
}
