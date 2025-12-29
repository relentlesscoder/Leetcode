package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3652 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/
 */
public class BestTimeToBuyAndSellStockUsingStrategy {

    // time O(n), space O(n)
    public long maxProfitPrefixSum(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        // 数组prefix1存按照原交易策略得到的前缀和，prefix2存全部按照卖出(strategy为1)
        // 策略得到的前缀和。
        long[] prefix1 = new long[n + 1], prefix2 = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix1[i + 1] = prefix1[i] + prices[i] * strategy[i];
            prefix2[i + 1] = prefix2[i] + prices[i];
        }
        long max = 0; // max表示利润的最大改变值
        for (int i = k - 1; i < n; i++) {
            // s1表示当前k天按照原策略交易的利润
            long s1 = prefix1[i + 1] - prefix1[i - k + 1];
            // s2表示当前k天的后k/2天按照新策略(strategy为1)交易的利润，因为前k/2天
            // strategy为0所以可以不算。
            long s2 = prefix2[i + 1] - prefix2[i - k / 2 + 1];
            // 计算两者的差值并更新最大差值
            max = Math.max(max, s2 - s1);
        }
        // 答案即为原总利润加上最大利润差
        return prefix1[n] + max;
    }

    // time O(n), space O(1)
    public long maxProfitFixedLengthSlidingWindow(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long total = 0, // 按原策略交易的总利润
                sum = 0; // 改变交易策略后当前k天的利润改变量
        // 预先计算第一个k天
        for (int i = 0; i < k / 2; i++) {
            int ps = prices[i] * strategy[i];
            total += ps;
            sum -= ps; // sum += p * (0 - s)
        }
        for (int i = k / 2; i < k; i++) {
            int p = prices[i], s = strategy[i];
            total += p * s;
            sum += p * (1 - s);
        }
        // maxSum表示利润的最大改变值
        long maxSum = Math.max(sum, 0);
        // 维护一个大小为k的滑动窗口，当窗口右端点处于索引i时:
        //   1. 改变索引i的策略strategy[i]为1 - p * (1 - s)
        //   2. 改变索引i - k/2的策略1为0 - prices[i - k / 2] * (0 - 1)
        //   3. 改变索引i - k的策略0为原策略strategy[i - k] - prices[i - k] * strategy[i - k]
        //
        // e.g.:
        // 0 1 2 3 4 5 6 7 8 9
        // 0 0 0 1 1 1
        //   0 0 0 1 1 1
        //     0 0 0 1 1 1
        for (int i = k; i < n; i++) {
            int p = prices[i], s = strategy[i];
            total += p * s; // 维护原总利润
            // 计算利润变化量
            sum += p * (1 - s) - prices[i - k / 2] + prices[i - k] * strategy[i - k];
            // 更新利润变化量的最大值
            maxSum = Math.max(maxSum, sum);
        }
        // 答案即为原总利润加上最大利润差
        return total + maxSum;
    }
}
