package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2025.
 * #2407 https://leetcode.com/problems/longest-increasing-subsequence-ii/
 */
public class LongestIncreasingSubsequenceII {

    // time O(n * log(MAX)), space O(MAX)
    public int lengthOfLIS(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        for (int num : nums) { // O(n)
            max = Math.max(max, num);
        }
        SegmentTree st = new SegmentTree(max + 1);
        for (int i = 0; i < n; i++) { // O(n)
            int len = st.query(Math.max(nums[i] - k, 0), Math.max(nums[i] - 1, 0)); // O(log(MAX))
            st.update(nums[i], len + 1); // O(log(MAX))
        }
        return st.queryAll();
    }

    private static class SegmentTree {

        private final int n;
        private final int[] tree;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[2 << (32 - Integer.numberOfLeadingZeros(n))];
        }

        public int queryAll() {
            return tree[1];
        }

        public int query(int start, int end) {
            return query(1, 0, n - 1, start, end);
        }

        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        private void update(int node, int left, int right, int index, int val) {
            if (left == right) {
                tree[node] = val;
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

        private int query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            int mid = (left + right) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end);
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            int leftRes = query(node * 2, left, mid, start, end);
            int rightRes = query(node * 2 + 1, mid + 1, right, start, end);
            return Math.max(leftRes, rightRes);
        }

        private void maintain(int node) {
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }
}
