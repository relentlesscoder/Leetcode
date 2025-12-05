package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0360 https://leetcode.com/problems/sort-transformed-array/
 */
public class SortTransformedArray {

    // time O(n), space O(1)
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        // Use the curve to sort, https://en.wikipedia.org/wiki/Quadratic_function
        int n = nums.length;
        int[] res = new int[n];
        int k = a >= 0 ? n - 1 : 0;
        for (int i = 0, j = n - 1; i <= j; ) {
            int left = calc(nums[i], a, b, c);
            int right = calc(nums[j], a, b, c);
            if (a >= 0) {
                res[k--] = Math.max(left, right);
                if (left >= right) { // Fill the larger value first
                    i++;
                } else {
                    j--;
                }
            } else {
                res[k++] = Math.min(left, right);
                if (left < right) { // Fill the smaller value first
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }

    private int calc(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
