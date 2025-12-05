package org.wshuai.leetcode;

/**
 * Created by Wei on 09/24/2019.
 * #1031 https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/
 */
public class MaximumSumOfTwoNonOverlappingSubarrays {

    // time O(n), space O(n)
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        // Set max to value at index L + M
        int res = prefix[firstLen + secondLen];
        // max1 is the max left subarray sum ends with length firstLen
        int max1 = prefix[firstLen];
        // max2 is the max left subarray sum ends with length secondLen
        int max2 = prefix[secondLen];
        for (int i = firstLen + secondLen; i < n; i++) {
            // Maintain max1 (need to minus secondLen for the right subarray)
            max1 = Math.max(max1, prefix[i + 1 - secondLen] - prefix[i + 1 - firstLen - secondLen]);
            // Maintain max2 (need to minus firstLen for the right subarray)
            max2 = Math.max(max2, prefix[i + 1 - firstLen] - prefix[i + 1 - firstLen - secondLen]);
            // Answer is max(max(firstLen + secondLen), max(secondLen + firstLen))
            res = Math.max(res, Math.max(max1 + prefix[i + 1] - prefix[i + 1 - secondLen], max2 + prefix[i + 1] - prefix[i + 1 - firstLen]));
        }
        return res;
    }
}
