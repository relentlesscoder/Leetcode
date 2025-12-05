package org.wshuai.leetcode;

/**
 * Created by Wei on 01/11/2024.
 * #2600 https://leetcode.com/problems/k-items-with-the-maximum-sum/
 */
public class KItemsWithTheMaximumSum {

    // time O(1), space O(1)
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int res = 0;
        int[][] nums = new int[][] {
                {numOnes, 1},
                {numZeros, 0},
                {numNegOnes, -1}
        };
        for (int[] num : nums) {
            if (k > 0) {
                res += Math.min(num[0], k) * num[1];
                k -= Math.min(num[0], k);
            }
        }
        return res;
    }
}
