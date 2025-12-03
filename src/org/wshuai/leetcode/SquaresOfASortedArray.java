package org.wshuai.leetcode;

/**
 * Created by Wei on 08/08/2019.
 * #0977 https://leetcode.com/problems/squares-of-a-sorted-array/
 */
public class SquaresOfASortedArray {

    // time O(n), space O(1)
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0, j = n - 1, k = n - 1; i <= j; ) {
            int a = nums[i] < 0 ? -nums[i] : nums[i];
            int b = nums[j] < 0 ? -nums[j] : nums[j];
            if (a <= b) {
                res[k--] = b * b;
                j--;
            } else {
                res[k--] = a * a;
                i++;
            }
        }
        return res;
    }
}
