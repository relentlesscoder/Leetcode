package org.wshuai.leetcode;

/**
 * Created by Wei on 08/23/2025.
 * #3097 https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/
 */
public class ShortestSubarrayWithOrAtLeastKII {

    // time O(n), space O(1)
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length, min = n + 1;
        int[] mask = new int[30];
        for (int i = 0, j = 0, or = 0; j < n; j++) {
            for (int b = 0; b < 30; b++) {
                if (((nums[j] >> b) & 1) > 0) {
                    mask[b]++;
                }
            }
            while (i <= j && convertToInt(mask) >= k) {
                for (int b = 0; b < 30; b++) {
                    if (((nums[i] >> b) & 1) > 0) {
                        mask[b]--;
                    }
                }
                min = Math.min(min, j - i + 1);
                i++;
            }
        }
        return min > n ? -1 : min;
    }

    private int convertToInt(int[] mask) {
        int res = 0;
        for (int i = 0; i < 30; i++) {
            if (mask[i] > 0) {
                res += (1 << i);
            }
        }
        return res;
    }
}
