package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2025.
 * #3152 https://leetcode.com/problems/special-array-ii/
 */
public class SpecialArrayII {

    // time O(m + n), space O(m + n)
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        boolean[] res = new boolean[m];
        // ends[i]存以i开头的最长奇偶子数组的结束位置
        int[] ends = new int[n];
        // 逆序遍历数组
        for (int i = n - 1; i >= 0; i--) {
            // 当前数可以延续当前的最长子数组
            if (i < n - 1 && ((nums[i] ^ nums[i + 1]) & 1) == 1) {
                ends[i] = ends[i + 1];
            } else { // 不能延续
                ends[i] = i;
            }
        }
        for (int i = 0; i < m; i++) {
            int start = queries[i][0], end = queries[i][1];
            res[i] = ends[start] >= end;
        }
        return res;
    }

    // time O(m + n), space O(m + n)
    public boolean[] isArraySpecialPrefixSum1(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        boolean[] res = new boolean[m];
        // prefix[i]存0到i的奇偶差异性不合法相邻元素的对数
        int[] prefix = new int[n];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + 1 - ((nums[i] ^ nums[i - 1]) & 1);
        }
        for (int i = 0; i < m; i++) {
            int start = queries[i][0], end = queries[i][1];
            res[i] = prefix[start] == prefix[end];
        }
        return res;
    }

    // time O(m + n), space O(m + n)
    public boolean[] isArraySpecialPrefixSum2(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        boolean[] res = new boolean[m];
        // 示例1: nums = [3,4,1,2,6], queries = [[0,4]]
        // 计算每对相邻数字的不同奇偶性
        for (int i = 1; i < n; i++) {
            // 异或
            nums[i - 1] ^= nums[i];
            // 取最低位
            nums[i - 1] &= 1;
        }
        // 数组nums状态为 [1,1,1,0,6]
        // 建立前缀和数组
        int[] prefix = new int[n];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        // 前缀和数组为 [0,1,2,3,3]
        for (int i = 0; i < m; i++) {
            int start = queries[i][0], end = queries[i][1];
            // prefix[end] - prefix[start] 即为区间 [start,end] 中的不同奇偶性
            // 的对数，如果此对数等于 end - start 则当前区间合法。
            res[i] = prefix[end] - prefix[start] == end - start;
        }
        return res;
    }
}
