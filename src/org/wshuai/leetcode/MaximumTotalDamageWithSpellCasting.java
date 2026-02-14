package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/01/2025.
 * #3186 https://leetcode.com/problems/maximum-total-damage-with-spell-casting/
 */
public class MaximumTotalDamageWithSpellCasting {

    // time O(n * log(n)), space O(m)
    public long maximumTotalDamageDPConcise(int[] power) {
        // 简化版的 DP
        // 用哈希表统计数组值相同的元素的频率
        Map<Integer, Integer> powerMap = new HashMap<>();
        for (int x : power) {
            powerMap.merge(x, 1, Integer::sum);
        }
        // 去重并排序
        int[] sorted = Arrays.stream(power).distinct().sorted().toArray();
        int m = sorted.length, j = 0;
        long[] dp = new long[m + 1]; // dp 数组中的索引 1 对应 sorted 数组中的索引 0
        for (int i = 0; i < m; i++) {
            int p = sorted[i];
            // 找到数组中大于等于 power - 2 的索引 j, 煮鱼这个索引对应与 dp 数组中最大的小
            // 于 power - 2 的元素的索引的结果
            while (sorted[j] < p - 2) {
                j++;
            }
            dp[i + 1] = Math.max(dp[i], dp[j] + (long) p * powerMap.get(p));
        }
        return dp[m];
    }

    // time O(n * log(n)), space O(m)
    public long maximumTotalDamageDPVerbose(int[] power) {
        // 去重并排序
        int[] sorted = Arrays.stream(power).distinct().sorted().toArray();
        int m = sorted.length;
        // 用哈希表统计数组值相同的元素的和
        Map<Integer, Long> sum = new HashMap<>();
        for (int x : power) {
            sum.merge(x, (long) x, Long::sum);
        }
        long[] dp = new long[m + 3]; // 设 dp 数组长度为 m + 3 以便于计算
        for (int i = 3; i <= m + 2; i++) { // dp 数组中的索引 3 对应 sorted 数组中的索引 0
            long c = dp[i - 1]; // 不选
            // 如果 x - 1 和 x - 2 同时存在，则 dp[i - 1] 和 dp[i - 2] 必然分别对应选它们的结果。如果
            // 要选当前元素则不能选它们 - dp[i - 3] + sum
            if (sum.containsKey(sorted[i - 3] - 2) && sum.containsKey(sorted[i - 3] - 1)) {
                c = Math.max(c, dp[i - 3] + sum.get(sorted[i - 3]));
                // 如果 x - 1 和 x - 2 有一个存在，则 dp[i - 1] 必然对应选它的结果。如果要选当前元素则不
                // 能选它 - dp[i - 2] + sum
            } else if (sum.containsKey(sorted[i - 3] - 2) || sum.containsKey(sorted[i - 3] - 1)) {
                c = Math.max(c, dp[i - 2] + sum.get(sorted[i - 3]));
            } else { // 都不存在直接选 - 注意是加上不是比大小 (贪心)
                c += sum.get(sorted[i - 3]);
            }
            dp[i] = c;
        }
        return dp[m + 2];
    }

    // time O(n * log(n)), space O(n)
    public long maximumTotalDamageDFSWithMemorization(int[] power) {
        // 哈希表统计频率
        Map<Integer, Integer> powerMap = new HashMap<>();
        for (int x : power) {
            powerMap.merge(x, 1, Integer::sum);
        }
        int m = powerMap.size();
        // 去重并排序
        int[] sorted = Arrays.stream(power).distinct().sorted().toArray();
        long[] memo = new long[m];
        Arrays.fill(memo, -1);
        return dfs(sorted, powerMap, memo, m - 1);
    }

    private long dfs(int[] sorted, Map<Integer, Integer> powerMap, long[] memo, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int power = sorted[i], j = i;
        // 找到数组中小于 power - 2 的索引 j
        while (j > 0 && sorted[j - 1] >= power - 2) {
            j--;
        }
        // 选或者不选
        return memo[i] = Math.max(dfs(sorted, powerMap, memo, i - 1), // 不选
                dfs(sorted, powerMap, memo, j - 1) + (long) power * powerMap.get(power)); // 选
    }
}
