package org.wshuai.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/19/2019.
 * #1074 https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
 */
public class NumberOfSubmatricesThatSumToTarget {

    // time O(n * m^2), space O(n)
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        // 固定起始行 r1 ，遍历每个结束行 r2。维护一个数组 nums 计算将多行压缩到
        // 1 行的列的和。把问题转化为求子数组和等于目标值的个数 #0560。
        int res = 0, m = matrix.length, n = matrix[0].length;
        // 起始行 r1
        for (int r1 = 0; r1 < m; r1++) {
            int[] nums = new int[n];
            // 结束行 r2
            for (int r2 = r1; r2 < m; r2++) {
                // 维护每一列的和
                for (int c = 0; c < n; c++) {
                    nums[c] += matrix[r2][c];
                }
                // 求子数组和等于目标值的个数
                res += countSubarraySum(nums, target);
            }
        }
        return res;
    }

    private int countSubarraySum(int[] nums, int target) {
        int res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            res += map.getOrDefault(sum - target, 0);
            map.merge(sum, 1, Integer::sum);
        }
        return res;
    }
}
