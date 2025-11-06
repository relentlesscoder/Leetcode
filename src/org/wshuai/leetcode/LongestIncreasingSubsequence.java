package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/14/2016.
 * #0300 https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {

    // time O(n * log(m)), space O(m)
    public int lengthOfLISBinaryIndexedTree(int[] nums) {
        int res = 0, n = nums.length;
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray();
        int m = sorted.length;
        BIT bit = new BIT(m);
        for (int i = 0; i < n; i++) {
            // Find index in BIT for all nums[j] <= nums[i]
            int index = binarySearch(sorted, nums[i] + 1);
            // Find LIS ends with all nums[j] < nums[i] (index - 1)
            int ans = bit.query(index - 1);
            // Extend the LIS
            res = Math.max(res, ans + 1);
            // Update in BIT
            bit.update(index, ans + 1);
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

        private int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] = Math.max(tree[index], val);
                index += index & -index;
            }
        }

        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res = Math.max(tree[index], res);
                index -= index & -index;
            }
            return res;
        }
    }

    // time O(n * log(n)), space O(n)
    public int lengthOfLISGreedy(int[] nums) {
        // https://www.bilibili.com/video/BV1ub411Q7sB/
        int len = 0;
        int[] tails = new int[nums.length];
        for (int num : nums) {
            // Find the first index i so that num <= tails[i]
            int pos = bisect(num, tails, len);
            // Try replacing existing element with smaller values to increase the
            // chance for extending LIS
            tails[pos] = num;
            if (pos == len) { // Extend the LIS
                len++;
            }
        }
        return len;
    }

    private int bisect(int num, int[] tails, int high) {
        int low = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (tails[mid] < num) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // time O(n^2), space O(n)
    public int lengthOfLISDP(int[] nums) {
        int res = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // time O(n^2), space O(n)
    public int lengthOfLISMemorization(int[] nums) {
        int res = 0;
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, dfs(i, nums, memo));
        }
        return res;
    }

    private int dfs(int i, int[] nums, int[] memo) {
        if (memo[i] != -1) {
            return memo[i];
        }
        int res = 0;
        for (int j = i - 1; j >= 0; j--) {
            if (nums[i] > nums[j]) {
                res = Math.max(res, dfs(j, nums, memo));
            }
        }
        return memo[i] = res + 1;
    }
}
