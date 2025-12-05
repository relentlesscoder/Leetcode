package org.wshuai.leetcode;

/**
 * Created by Wei on 07/24/2017.
 * #0321 https://leetcode.com/problems/create-maximum-number/
 */
public class CreateMaximumNumber {

    // time O(k * (m + n + k^2)), space O(k)
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] max = new int[]{-1};
        for (int i = Math.max(k - n, 0); i <= Math.min(k, m); i++) { // O(k)
            int[] curr = merge(getMax(nums1, i), getMax(nums2, k - i)); // O(m + n)^2
            if (greater(curr, max, 0, 0)) { // O(m + n)
                max = curr;
            }
        }
        return max;
    }

    private int[] getMax(int[] nums, int k) {
        // same as #1673
        if (k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        if (k >= n) {
            return nums;
        }
        int[] stack = new int[k];
        for (int i = 0, j = 0; i < n; i++) {
            // n - i + 1 >= k - j
            while (j >= 1 && stack[j - 1] < nums[i] && n - i + j > k) {
                j--;
            }
            if (j < k) {
                stack[j++] = nums[i];
            }
        }
        return stack;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] res = new int[m + n];
        for (int i = 0, j = 0, k = 0; i < m || j < n; k++) {
            if (greater(nums1, nums2, i, j)) {
                res[k] = nums1[i++];
            } else {
                res[k] = nums2[j++];
            }
        }
        return res;
    }

    private boolean greater(int[] nums1, int[] nums2, int i, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
}
