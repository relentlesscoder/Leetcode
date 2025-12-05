package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/11/2025.
 * #3410 https://leetcode.com/problems/maximize-subarray-sum-after-removing-all-occurrences-of-one-element/
 */
public class MaximizeSubarraySumAfterRemovingAllOccurrencesOfOneElement {

    private long maxSum = Long.MIN_VALUE / 2;
    private long prefixSum = 0;
    private final Map<Integer, Long> last = new HashMap<>();

    // time O(n), space O(1)
    public long maxSubarraySum(int[] nums) {
        // https://leetcode.cn/link/?target=https%3A%2F%2Fwww.bilibili.com%2Fvideo%2FBV1SzrAYMESJ%2F%3Ft%3D23m43s
        // Max subarray sum with suffix end at index i - suffix[i], assume next
        // index with value nums[i] is k:
        //   1. f[i + 1, j], j > k - f is max subarray sum without
        //      deleting numbers #0053
        //   2. suffix[k] + sum(i + 1, k - 1) - converted to a sub problem
        // Prefix calculation is the same.
        int n = nums.length;
        long[] pre = new long[n];
        // Calculate max subarray sum with prefix end at i if we remove nums[i]
        for (int i = 0; i < n; i++) {
            pre[i] = update(nums[i]);
        }
        // Reset values
        long res = Long.MIN_VALUE;
        maxSum = Long.MIN_VALUE / 2;
        prefixSum = 0;
        last.clear();
        for (int i = n - 1; i >= 0; i--) {
            // Calculate max subarray sum with suffix end at i if we remove nums[i]
            long suf = update(nums[i]);
            // maxSubarraySum_d (with deletion) = max(pre[i] + suf[i], pre[i], suf[i])
            // maxSubarraySum (without deletion) = maxSum
            // res = Math.max(maxSubarraySum_d, maxSubarraySum)
            res = Math.max(res, Math.max(maxSum, Math.max(pre[i] + suf, Math.max(pre[i], suf))));
        }
        return res;
    }

    private long update(int num) {
        // res is suffix[i]
        long res = maxSum;
        // Maintain max subarray sum f[i] = max(f[i-1], 0) + num
        maxSum = Math.max(maxSum, 0) + num;
        if (last.containsKey(num)) { // If nums[i] is seen
            // Given suffix[i] = suffix[k] + sum(i + 1, k - 1), we have:
            // res - prefixSum[k + 1] + prefixSum[i + 1] and res = suffix[k]
            // so we can translate above to:
            // suffix[k] + prefixSum[i + 1] - prefixSum[k + 1]
            // = suffix[k] + sum(i + 1, k - 1) + nums[k] since k is deleted
            // nums[k] = 0, the formula becomes:
            // suffix[k] + sum(i + 1, k - 1)
            res = Math.max(res, last.get(num) + prefixSum);
        }
        // Maintain prefix sum
        prefixSum += num; // s[i+1] = s[i] + num
        last.put(num, res - prefixSum);
        return res;
    }

    // time O(n * log(n)), space O(n)
    public long maxSubarraySumSegmentTree(int[] nums) {
        // Same idea as #0053
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        SegmentTree st = new SegmentTree(nums);
        // Do not delete any numbers
        long res = st.getMaxSum();
        // If all numbers are non-positive then just return the maximum value
        if (res <= 0) {
            return res;
        }
        for (List<Integer> idxs : map.values()) {
            // Try deleting number by replacing all of its occurrences by 0
            for (int idx : idxs) {
                st.update(idx, 0);
            }
            res = Math.max(res, st.getMaxSum());
            // Reset values back
            for (int idx : idxs) {
                st.update(idx, nums[idx]);
            }
        }
        return res;
    }

    private record State(long leftSum, long rightSum, long rangeSum, long maxSum) {}

    private static class SegmentTree {

        private final int n;
        private final State[] tree;

        public SegmentTree(int[] nums) {
            this.n = nums.length;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
            tree = new State[size];
            build(1, 0, n - 1, nums);
        }

        public long getMaxSum() {
            return tree[1].maxSum;
        }

        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        private void update(int node, int left, int right, int index, int val) {
            if (left == right) {
                tree[node] = new State(val, val, val, val);
                return;
            }
            int mid = (left + right) / 2;
            if (index <= mid) {
                update(node * 2, left, mid, index, val);
            } else {
                update(node * 2 + 1, mid + 1, right, index, val);
            }
            maintain(node);
        }

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                tree[node] = new State(nums[left], nums[left], nums[left], nums[left]);
                return;
            }
            int mid = (left + right) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            maintain(node);
        }

        private void maintain(int node) {
            State leftState = tree[node * 2];
            State rightState = tree[node * 2 + 1];
            long rangeSum = leftState.rangeSum + rightState.rangeSum;
            long leftSum = Math.max(leftState.leftSum, leftState.rangeSum + rightState.leftSum);
            long rightSum = Math.max(rightState.rightSum, rightState.rangeSum + leftState.rightSum);
            long maxSum = Math.max(leftState.maxSum, Math.max(rightState.maxSum, leftState.rightSum + rightState.leftSum));
            tree[node] = new State(leftSum, rightSum, rangeSum, maxSum);
        }
    }
}
