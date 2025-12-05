package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/08/2025.
 * #2921 https://leetcode.com/problems/maximum-profitable-triplets-with-increasing-prices-ii/
 */
public class MaximumProfitableTripletsWithIncreasingPricesII {

    // time O(n * log(n)), space O(n)
    public int maxProfit(int[] prices, int[] profits) {
        int[] sorted = Arrays.stream(prices).distinct().sorted().toArray();
        int res = -1, n = prices.length, m = sorted.length;
        BIT bit = new BIT(m);
        int[] prefix = new int[n];
        Arrays.fill(prefix, -1);
        // For each index i, find the max profit[j] with price[j] < price[i] on it's left
        for (int i = 0; i < n; i++) {
            int index = binarySearch(sorted, prices[i] + 1);
            prefix[i] = bit.query(index - 1);
            bit.update(index, profits[i]);
        }
        bit = new BIT(m);
        // For each index i, find the max profit[j] with price[j] > price[i] on it's right
        for (int i = 0; i < n; i++) {
            // Map the index reversely in BIT
            int index = m + 1 - binarySearch(sorted, prices[n - 1 - i] + 1);
            int max = bit.query(index - 1);
            if (max > 0 && prefix[n - 1 - i] > 0) {
                res = Math.max(res, profits[n - 1 - i] + max + prefix[n - 1 - i]);
            }
            bit.update(index, profits[n - 1 - i]);
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static class BIT {

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
            Arrays.fill(tree, -1);
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] = Math.max(tree[index], val);
                index += index & -index;
            }
        }

        public int query(int index) {
            int res = -1;
            while (index > 0) {
                res = Math.max(res, tree[index]);
                index -= index & -index;
            }
            return res;
        }
    }
}
