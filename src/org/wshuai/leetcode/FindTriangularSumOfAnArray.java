package org.wshuai.leetcode;

/**
 * Created by Wei on 09/07/2023.
 * #2221 https://leetcode.com/problems/find-triangular-sum-of-an-array/description/
 */
public class FindTriangularSumOfAnArray {

    // time O(n^2), space O(1)
    public int triangularSum(int[] nums) {
        for (int i = nums.length; i > 1; i--) {
            for (int j = 1; j < nums.length; j++) {
                nums[j - 1] = (nums[j] + nums[j - 1]) % 10;
            }
        }
        return nums[0];
    }

    // time O(n^2), space O(n)
    public int triangularSumDFS(int[] nums) {
        return dfs(nums, nums.length);
    }

    private int dfs(int[] nums, int n) {
        if (n == 1) {
            return nums[0];
        }
        for (int i = 1; i < n; i++) {
            nums[i - 1] = (nums[i - 1] + nums[i]) % 10;
        }
        return dfs(nums, n - 1);
    }
}
