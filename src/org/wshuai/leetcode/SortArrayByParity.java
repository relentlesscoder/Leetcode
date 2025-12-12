package org.wshuai.leetcode;

/**
 * Created by Wei on 08/08/2019.
 * #0905 https://leetcode.com/problems/sort-array-by-parity/
 */
public class SortArrayByParity {

    // time O(n), space O(1)
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
        return nums;
    }
}
