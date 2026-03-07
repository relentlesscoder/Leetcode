package org.wshuai.leetcode.binarysearch;

import java.util.TreeSet;

/**
 * Created by Wei on 08/26/2019.
 * #0363 https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 */
public class MaxSumOfRectangleNoLargerThanK {

    // time O(m^2 * n * log(n)), space O(n)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        // #1074 相似题
        // 固定起始行 s ，遍历每个结束行 r。维护一个数组 nums 计算将多行压缩到
        // 1 行的列的和。
        // 题目转化为求一位数组中和不超过 k 的子数组的最大值。
        //   s[i] - s[j] <= k
        //   s[j] >= s[i] - k
        // 因为我们需要最大值，所以满足上式的 s[j] 越小越好。
        int res = Integer.MIN_VALUE, m = matrix.length, n = matrix[0].length;
        for (int s = 0; s < m; s++) { // O(m)
            int[] nums = new int[n];
            for (int r = s; r < m; r++) { // O(m)
                for (int c = 0; c < n; c++) { // O(n)
                    nums[c] += matrix[r][c];
                }
                // 维护一个有序集合存出现过的前缀和
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for (int i = 0, sum = 0; i < n; i++) { // O(n)
                    sum += nums[i];
                    // 利用有序集合找到满足 s[j] >= s[i] - k 的 s[j] 的最小值
                    Integer ceil = set.ceiling(sum - k); // O(log(n))
                    if (ceil != null) {
                        if (sum - ceil == k) { // 优化: k 是可能的最大值
                            return k;
                        }
                        res = Math.max(res, sum - ceil);
                    }
                    set.add(sum); // O(log(n))
                }
            }
        }
        return res;
    }
}
