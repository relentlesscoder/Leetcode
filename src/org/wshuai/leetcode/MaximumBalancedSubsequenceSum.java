package org.wshuai.leetcode;

import java.util.stream.IntStream;

/**
 * Created by Wei on 11/06/2025.
 * #2926 https://leetcode.com/problems/maximum-balanced-subsequence-sum/
 */
public class MaximumBalancedSubsequenceSum {

    // time O(n * log(m)), space O(m)
    public long maxBalancedSubsequenceSum(int[] nums) {
        long res = 0;
        int n = nums.length, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
        }
        // No positive number found, return the max value (single element sequence)
        if (max <= 0) {
            return max;
        }
        int[] sorted = IntStream.range(0, n).map(i -> nums[i] - i).distinct().sorted().toArray();
        int m = sorted.length;
        // Use BIT to store maximum sum for balanced subsequence ends at each index
        BIT bit = new BIT(m);
        for (int i = 0; i < n; i++) {
            // skip non-positive numbers since they do not help to increase the result
            if (nums[i] <= 0) {
                continue;
            }
            int index = binarySearch(sorted, nums[i] - i + 1);
            // Extend the balanced subsequence
            long ans = bit.query(index) + nums[i];
            res = Math.max(res, ans);
            bit.update(index, ans);
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

        private long[] tree;

        public BIT(int n) {
            tree = new long[n + 1];
        }

        public void update(int index, long val) {
            while (index < tree.length) {
                tree[index] = Math.max(tree[index], val);
                index += index & -index;
            }
        }

        public long query(int index) {
            long res = 0;
            while (index > 0) {
                res = Math.max(tree[index], res);
                index -= index & -index;
            }
            return res;
        }
    }
}
