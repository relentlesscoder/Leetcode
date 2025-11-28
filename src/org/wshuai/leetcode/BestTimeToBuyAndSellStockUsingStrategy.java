package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3652 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/
 */
public class BestTimeToBuyAndSellStockUsingStrategy {

    // time O(n), space O(1)
    public long maxProfitFixedLengthSlidingWindow(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long total = 0, // Total profit for original strategy without modification
                sum = 0; // Sum of the profit delta for current sliding window modification
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
        // maxSum represents the maximum delta increase from original strategy
        // without modification so if we can't increase the profit (maximum delta
        // is negative then don't change it - 0)
        long maxSum = Math.max(sum, 0);
        // Maintaining the sliding window from strategy[k]:
        //   1. Modify the strategy for index i from strategy[i] to 1
        //   2. Modify the strategy for index i - k/2 from 1 to 0
        //   3. Modify the strategy for index i - k from 0 to strategy[i - k] (original)
        //
        // e.g.:
        // 0 1 2 3 4 5 6 7 8 9
        // 0 0 0 1 1 1
        //   0 0 0 1 1 1
        //     0 0 0 1 1 1
        for (int i = k; i < n; i++) {
            int p = prices[i], s = strategy[i];
            total += p * s; // Add profit to the total
            // Calculate widow delta
            sum += p * (1 - s) - prices[i - k / 2] + prices[i - k] * strategy[i - k];
            maxSum = Math.max(maxSum, sum);
        }
        return total + maxSum;
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
