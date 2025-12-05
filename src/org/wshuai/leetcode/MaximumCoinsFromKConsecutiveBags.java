package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/01/2025.
 * #3413 https://leetcode.com/problems/maximum-coins-from-k-consecutive-bags/
 */
public class MaximumCoinsFromKConsecutiveBags {

    // time O(n * log(n)), space O(1)
    public long maximumCoins(int[][] coins, int k) {
        // Same idea as #2271
        long res = collect(coins, k);
        int n = coins.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int[] temp = coins[i];
            coins[i] = coins[j];
            coins[j] = temp;
        }
        for (int[] c : coins) {
            int temp = -c[0];
            c[0] = -c[1];
            c[1] = temp;
        }
        return Math.max(res, collect(coins, k));
    }

    private long collect(int[][] coins, int k) {
        long res = 0, collected = 0;
        int n = coins.length;
        Arrays.sort(coins, (a, b) -> a[0] - b[0]);
        for (int left = 0, right = 0; right < n; right++) {
            int[] tail = coins[right];
            int l = tail[0], r = tail[1], v = tail[2];
            collected += (long) v * (r - l + 1);

            while (r - k + 1 > coins[left][1]) {
                collected -= (long) coins[left][2] * (coins[left][1] - coins[left][0] + 1);
                left++;
            }
            long uncollected = Math.max(0, (long) coins[left][2] * (r - k + 1 - coins[left][0]));
            res = Math.max(res, collected - uncollected);
        }
        return res;
    }
}
