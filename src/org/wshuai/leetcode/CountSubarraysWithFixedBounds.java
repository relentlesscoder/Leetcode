package org.wshuai.leetcode;

/**
 * Created by Wei on 09/22/2025.
 * #2444 https://leetcode.com/problems/count-subarrays-with-fixed-bounds/
 */
public class CountSubarraysWithFixedBounds {

    // time O(n), space O(1)
    public long countSubarraysSimplified(int[] nums, int minK, int maxK) {
        // see detailed explanation in original solution below
        long res = 0;
        int indexMinK = -1, indexMaxK = -1;
        for (int i = -1, j = 0; j < nums.length; j++) {
            if (nums[j] < minK || nums[j] > maxK) {
                i = j;
                continue;
            }
            if (nums[j] == minK) {
                indexMinK = j;
            }
            if (nums[j] == maxK) {
                indexMaxK = j;
            }
            res += Math.max(0, Math.min(indexMinK, indexMaxK) - i);
        }
        return res;
    }

    // time O(n), space O(1)
    public long countSubarrays(int[] nums, int minK, int maxK) {
        if (minK == maxK) {
            return countSpecialSubarrays(nums, minK);
        }
        long res = 0;
        // indexMinK is the last index of minK
        // indexMaxK is the last index of maxK
        int indexMinK = -1, indexMaxK = -1;
        for (int i = -1, j = 0; j < nums.length; j++) {
            // i points to last violating number - nums[i] < minK or nums[i] > maxK
            // j points to the current number
            if (nums[j] < minK || nums[j] > maxK) {
                // reset last index of minK and maxK
                indexMinK = -1;
                indexMaxK = -1;
                // update i
                i = j;
                continue;
            }
            if (nums[j] == minK) {
                // if nums[j] equals to minK, all numbers between i + 1 and
                // last index of maxK can be the head of a fixed bounds subarray
                // example nums = [1,7,8,6,5,4,7], minK = 4, maxK = 8
                // For index 5, i = 0 and fixed bound subarray ends with 4 (index 5) is
                // indexMaxK (2) - i (0) = 2:
                // 7,8,6,5,4
                // 8,6,5,4
                if (indexMaxK != -1) {
                    res += indexMaxK - i;
                }
                indexMinK = j;
            } else if (nums[j] == maxK) {
                // based on the above analysis, if nums[j] equals to minK the count
                // of fixed bounds subarray ends with nums[j] is indexMinK - i
                if (indexMinK != -1) {
                    res += indexMinK - i;
                }
                indexMaxK = j;
            } else if (indexMinK != -1 && indexMaxK != -1) {
                // otherwise, all numbers between i + 1 and min(indexMinK, indexMaxK)
                // can be the head of a fixed bounds subarray because it is guaranteed
                // that at least 1 minK and 1 maxK exists in the subarray and there is
                // no violating numbers
                // example nums = [1,7,8,6,5,4,7], minK = 4, maxK = 8
                // For index 6, i = 0 and fixed bound subarray ends with 7 (index 6) is
                // min(indexMinK, indexMaxK) (2) - i (0) = 2:
                // 7,8,2,5,4,7
                // 8,2,5,4,7
                res += Math.min(indexMinK, indexMaxK) - i;
            }
        }
        return res;
    }

    private long countSpecialSubarrays(int[] nums, int k) {
        long res = 0;
        for (int i = -1, j = 0; j < nums.length; j++) {
            if (nums[j] < k || nums[j] > k) {
                i = j;
                continue;
            }
            // for special case minK == maxK, all numbers between i + 1 and j
            // can be the head of a fixed bound subarray
            if (nums[j] == k) {
                res += j - i;
            }
        }
        return res;
    }
}
