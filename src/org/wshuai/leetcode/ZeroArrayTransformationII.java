package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 06/29/2025.
 * #3356 https://leetcode.com/problems/zero-array-transformation-ii/
 */
public class ZeroArrayTransformationII {

    // time O(m + n), space O(n)
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length, sum = 0, k = 0;
        int[] diff = new int[n + 1];
        for (int i = 0; i < n; i++) {
            while (sum + diff[i] < nums[i]) {
                k++;
                if (k > queries.length) {
                    return -1;
                }
                int left = queries[k - 1][0], right = queries[k - 1][1], val = queries[k - 1][2];
                // ignore the diff if right is less than current index
                if (right >= i) {
                    diff[Math.max(left, i)] += val; // add diff to current index if left is less than current index
                    diff[right + 1] -= val;
                }
            }
            sum += diff[i];
        }
        return k;
    }

    // time O(log(m) * n), space O(n)
    public int minZeroArrayBinarySearch(int[] nums, int[][] queries) {
        boolean allZero = true;
        for (int num : nums) {
            if (num != 0) {
                allZero = false;
                break;
            }
        }
        if (allZero) {
            return 0;
        }
        int n = queries.length, low = 0, high = n - 1;
        int[] count = new int[nums.length + 1];
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canTransform(nums, queries, count, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return canTransform(nums, queries, count, low) ? low + 1 : -1;
    }

    private boolean canTransform(int[] nums, int[][] queries, int[] count, int k) {
        Arrays.fill(count, 0);
        int len = nums.length, sum = 0;
        for (int i = 0; i <= k; i++) {
            count[queries[i][0]] += queries[i][2];
            count[queries[i][1] + 1] -= queries[i][2];
        }
        for (int i = 0; i < len; i++) {
            sum += count[i];
            if (sum < nums[i]) {
                return false;
            }
        }
        return true;
    }
}
