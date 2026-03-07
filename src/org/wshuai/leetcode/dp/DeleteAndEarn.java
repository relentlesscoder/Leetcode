package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 10/10/2019.
 * #0740 https://leetcode.com/problems/delete-and-earn/
 */
public class DeleteAndEarn {

    // time O(n + MAX), space O(MAX)
    public int deleteAndEarnDP(int[] nums) {
        // 空间优化版
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        int[] sum = new int[max + 1];
        for (int x : nums) {
            sum[x] += x;
        }
        int s1 = 0, s2 = 0; // 用两个变量取代 dp 数组
        for (int i = 2; i <= max + 1; i++) {
            int c = 0;
            if (sum[i - 1] == 0) {
                c = s1;
            } else {
                c = Math.max(s1, s2 + sum[i - 1]);
            }
            s2 = s1;
            s1 = c;
        }
        return s1;
    }

    // time O(n + MAX), space O(MAX)
    public int deleteAndEarnDPWithArray(int[] nums) {
        int max = 0;
        // 统计数组的最大值
        for (int x : nums) {
            max = Math.max(max, x);
        }
        int[] sum = new int[max + 1], // 计算每个元素在数组中的总和
                // dp[i] 表示处理到第 i - 1 个元素能得到的最大值，这里设长度为 max + 2 方便后续处理
                dp = new int[max + 2];
        for (int x : nums) { // 计算元素和
            sum[x] += x;
        }
        // 从 2 开始计算， 索引 i 对应元素值 i - 1
        for (int i = 2; i <= max + 1; i++) {
            if (sum[i - 1] == 0) { // 如果元素不存在原数组中则肯定不选
                dp[i] = dp[i - 1];
                continue;
            }
            // 元素存在则选择选和不选的较大值
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sum[i - 1]);
        }
        return dp[max + 1];
    }
}
