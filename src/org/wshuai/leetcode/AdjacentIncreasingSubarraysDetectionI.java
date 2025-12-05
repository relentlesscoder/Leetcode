package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 10/01/2025.
 * #3349 https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i/
 */
public class AdjacentIncreasingSubarraysDetectionI {

    // time O(n), space O(1)
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int max = 0, n = nums.size(), prevCount = 0, count = 0;
        for (int i = 0; i < n; i++) {
            count++;
            if (i == n - 1 || nums.get(i) >= nums.get(i + 1)) {
                max = Math.max(max, Math.max(count / 2, Math.min(prevCount, count)));
                if (max >= k) {
                    return true;
                }
                prevCount = count;
                count = 0;
            }
        }
        return false;
    }

    // time O(n), space O(n)
    public boolean hasIncreasingSubarraysPrefixSum(List<Integer> nums, int k) {
        int n = nums.size();
        int[] maxLength = new int[n];
        maxLength[0] = 1; // maxLength[i] is max length of strictly increasing subarray ends at nums[i]
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                maxLength[i] = maxLength[i - 1] + 1;
            } else {
                maxLength[i] = 1;
            }
        }
        for (int i = maxLength.length - 1; i >= 2 * k - 1; i--) {
            if (Math.min(maxLength[i], maxLength[i - k]) >= k) {
                return true;
            }
        }
        return false;
    }
}
