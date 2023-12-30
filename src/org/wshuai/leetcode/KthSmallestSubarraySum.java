package org.wshuai.leetcode;

/**
 * Created by Wei on 12/29/2023.
 * #1918 https://leetcode.com/problems/kth-smallest-subarray-sum/
 */
public class KthSmallestSubarraySum {

    // time O(n * log(n)), space O(1)
    public int kthSmallestSubarraySum(int[] nums, int k) {
        int low = nums[0], high = 0;
        for (int num : nums) {
            low = Math.min(low, num);
            high += num;
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (numberOfSubarraysSumLessThan(mid, nums) < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int numberOfSubarraysSumLessThan(int threshold, int[] nums) {
        int res = 0, sum = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum > threshold) {
                sum -= nums[j++];
            }
            res += i - j + 1;
        }
        return res;
    }
}
