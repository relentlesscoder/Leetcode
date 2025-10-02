package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 10/01/2025.
 * #3350 https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii/
 */
public class AdjacentIncreasingSubarraysDetectionII {

    // time O(n), space O(1)
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int res = 0, n = nums.size(), prevCount = 0, count = 0;
        for (int i = 0; i < n; i++) {
            count++;
            if (i == n - 1 || nums.get(i) >= nums.get(i + 1)) {
                // two scenarios:
                // 1. two subarrays are adjacent but not in one strictly increasing subarray
                // [1,2,3,2,5,6]
                // 2. two subarrays are adjacent but in one strictly increasing subarray
                // [1,2,3,4,5,6]
                res = Math.max(res, Math.max(count / 2, Math.min(prevCount, count)));
                // note that if two subarrays are not adjacent, the prevCount will be reset
                // like [1,2,3,2,1,4,5,6], subarray [0,2] and subarray [4,6] does not count
                prevCount = count;
                count = 0;
            }
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int maxIncreasingSubarraysBinarySearch(List<Integer> nums) {
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
        int low = 1, high = n / 2;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            boolean canFindKAdjacent = false;
            for (int i = maxLength.length - 1; i >= 2 * mid - 1; i--) {
                if (Math.min(maxLength[i], maxLength[i - mid]) >= mid) {
                    canFindKAdjacent = true;
                    break;
                }
            }
            if (canFindKAdjacent) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
