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

    // time O(n * log(n)), space O(n)
    public long maxSubarraySum(int[] nums) {
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
