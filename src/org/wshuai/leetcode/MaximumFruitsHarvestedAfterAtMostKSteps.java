package org.wshuai.leetcode;

/**
 * Created by Wei on 11/30/2025.
 * #2106 https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps/
 */
public class MaximumFruitsHarvestedAfterAtMostKSteps {

    // time O(n), space O(1)
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int res = 0, n = fruits.length;
        for (int left = binarySearch(fruits, startPos - k), // Find the rightmost fruit we can reach
             right = left,
             sum = 0;
             right < n && fruits[right][0] <= startPos + k; right++) { // Right upper bound is startPos + k
            sum += fruits[right][1]; // Pick all the fruits
            while (fruits[right][0] * 2 - fruits[left][0] - startPos > k &&
                    fruits[right][0] - fruits[left][0] * 2 + startPos > k) {
                sum -= fruits[left][1]; // Discard the fruits that are too far left
                left++;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    private int binarySearch(int[][] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid][0] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
