package org.wshuai.leetcode;

/**
 * Created by Wei on 05/20/2021.
 * #1863 https://leetcode.com/problems/sum-of-all-subset-xor-totals/
 */
public class SumOfAllSubsetXORTotals {

    // time O(2^n)
    public int subsetXORSum(int[] nums) {
        return dfs(0, 0, nums);
    }

    private int dfs(int i, int cur, int[] nums){
        if(i == nums.length){
            return cur;
        }
        return dfs(i + 1, cur ^ nums[i], nums) + dfs(i + 1, cur, nums);
    }
}
