package org.wshuai.leetcode;

/**
 * Created by Wei on 08/08/2019.
 * #0922 https://leetcode.com/problems/sort-array-by-parity-ii/
 */
public class SortArrayByParityII {

    // time O(n), space O(1)
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = 1; i < n; i += 2) {
            if (nums[i] % 2 == 1) {
                while (nums[j] % 2 == 1) {
                    j += 2;
                }
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return nums;
    }

    // time O(n), space O(1)
    public int[] sortArrayByParityIITwoPass(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
        for (int i = 1; i < n / 2; i += 2) {
            int temp = nums[i];
            nums[i] = nums[n - 1 - i];
            nums[n - 1 - i] = temp;
        }
        return nums;
    }
}
