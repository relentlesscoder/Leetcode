package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3652 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/
 */
public class BestTimeToBuyAndSellStockUsingStrategy {

    // time O(n), space O(1)
    public long maxProfitSlidingWindow(int[] prices, int[] strategy, int k) {
        long res = 0, sum = 0;
        int n = prices.length, h = k >> 1;
        for (int i = 0; i < n; i++) {
            sum += strategy[i] * prices[i];
        }
        res = sum; // do not perform any modification to the strategy
        // calculate the sum for first k numbers applying the modification
        for (int i = 0; i < k; i++) {
            if (i < h) {
                sum -= strategy[i] * prices[i];
            } else {
                sum += (1 - strategy[i]) * prices[i];
            }
        }
        res = Math.max(res, sum);
        // maintaining the sliding window from strategy[k]:
        //   1. modify strategy[k] to 1
        //   2. modify strategy[i] to 0
        //   3. modify strategy[i + h] from 1 to 0
        //
        // example:
        // 0 0 0 1 1 1 s
        // s 0 0 0 1 1 1
        for (int i = 0, j = k; j < n; i++, j++) {
            sum += strategy[i] * prices[i];
            sum += (1 - strategy[j]) * prices[j];
            sum -= prices[i + h];
            res = Math.max(res, sum);
        }
        return res;
    }

    // time O(n), space O(1)
    public long maxProfitSlidingWindowVerbose(int[] prices, int[] strategy, int k) {
        long res = 0, sum = 0;
        int n = prices.length, h = k >> 1;
        for (int i = 0; i < n; i++) {
            sum += strategy[i] * prices[i];
        }
        res = sum; // do not perform any modification to the strategy
        // calculate the sum for first k numbers applying the modification
        for (int i = 0; i < k; i++) {
            if (i < h && strategy[i] == 0) {
                continue;
            }
            if (i >= h && strategy[i] == 1) {
                continue;
            }
            if (i < h) {
                sum += (strategy[i] == 1) ? -prices[i] : prices[i];
            } else {
                sum += (strategy[i] == 0) ? prices[i] : 2 * prices[i];
            }
        }
        res = Math.max(res, sum);
        // maintaining the sliding window from strategy[k]:
        //   1. modify strategy[k] to 1
        //   2. modify strategy[i] to 0
        //   3. modify strategy[i + h] from 1 to 0
        //
        // example:
        // 0 0 0 1 1 1 s
        // s 0 0 0 1 1 1
        for (int i = 0, j = k; j < n; i++, j++) {
            if (strategy[i] != 0) {
                sum += (strategy[i] == 1) ? prices[i] : -prices[i];
            }
            if (strategy[j] != 1) {
                sum += (strategy[j] == 0) ? prices[j] : 2 * prices[j];
            }
            sum -= prices[i + h];
            res = Math.max(res, sum);
        }
        return res;
    }

    // time O(n), space O(n)
    public long maxProfitPrefixSum(int[] prices, int[] strategy, int k) {
        long res = 0, sum = 0;
        int n = prices.length, h = k >> 1;
        long[][] prefix = new long[n + 1][2];
        for (int i = 0; i < n; i++) {
            sum += strategy[i] * prices[i];
            // prefix[i][0] stores sum of prices[x] (x in [0, i - 1])
            prefix[i + 1][0] = prices[i] + prefix[i][0];
            // prefix[i][1] stores sum of strategy[x] * prices[x] (x in [0, i - 1])
            prefix[i + 1][1] = sum;
        }
        res = sum; // do not perform any modification to the strategy
        for (int i = k - 1; i < n; i++) {
            // exclude original sum for current window of length k
            long windowSum = prefix[i + 1][1] - prefix[i - k + 1][1];
            long currentSum = sum - windowSum;
            // calculate sum for applying the modification to current window
            currentSum += prefix[i + 1][0] - prefix[i - h + 1][0];
            res = Math.max(res, currentSum);
        }
        return res;
    }
}
