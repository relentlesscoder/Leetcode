package org.wshuai.leetcode;

/**
 * Created by Wei on 05/20/2021.
 * #1863 https://leetcode.com/problems/sum-of-all-subset-xor-totals/
 */
public class SumOfAllSubsetXORTotals {

    // time O(n), space O(1)
    public int subsetXORSumBitMask(int[] nums) {
        // #0078
        int sum = 0, n = nums.length, m = 1 << n;
        for (int i = 0; i < m; i++) {
            int xor = 0;
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) > 0) {
                    xor ^= nums[j];
                }
            }
            sum += xor;
        }
        return sum;
    }

    // time O(2^n), space O(n)
    private int sum = 0;

    public int subsetXORSumBacktracking(int[] nums) {
        // #0078
        sum = 0;
        dfs(0, 0, nums);
        return sum;
    }

    private void dfs(int i, int s, int[] nums) {
        sum += s;
        for (int j = i; j < nums.length; j++) {
            dfs(j + 1, s ^ nums[j], nums);
        }
    }
}
