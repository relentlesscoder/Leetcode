package org.wshuai.leetcode;

/**
 * Created by Wei on 11/14/2025.
 * #2916 https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/
 */
public class SubarraysDistinctElementSumOfSquaresII {

    private static final int MOD = (int)1e9 + 7;

    // time O(n * log(n)), space O(n)
    public int sumCounts(int[] nums) {
        // Let's say sum(i) is the square sum for all subarrays ends at index i.
        // We have two cases here:
        //   1. nums[i] has not been visited before. Then for subarray starts at
        //      each index j in [0, i - 1] and ends at i the distinct count is
        //      increased by 1 compared to subarray ends at i - 1.
        //   2. nums[i] has been visited before. Let's say the index is j. then
        //      for each subarray starts at index j in [0, j] and ends at i the
        //      distinct count is unchanged. Only for subarray starts at indexes
        //      j in [j + 1, i - 1] and ends at i the distinct count is increased
        //      by 1.
        // So we use segment tree to store square sum f(0 -> i) starts at indexes in
        // [0, i] and ends at index i. When we iterate the array and for each i we
        // calculate the square sum difference with that of index i-1.
        // Since (x + 1)^2 - x^2 = 2x + 1, the total difference is
        // 2 * sum(j + 1, i - 1) + i - 1 - j - 1 + 1 = 2 * sum(j, i - 1) + i - j - 1.
        // And we still need to add sum(i, i) which is 1. We can just combine them to
        // 2 * sum(j + 1, i) + i - j.
        // so f(0 -> i) = f(0 -> i - 1) + 2 * sum(j + 1, i) + i - j when j is last index
        // with value equals to nums[i].
        int n = nums.length, max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] last = new int[max + 1];
        SegmentTree st = new SegmentTree(n);
        long res = 0, sum = 0;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            int j = last[num];
            // Add square sum difference to sum, now sum is
            // the new square sum for f(0 -> i)
            sum += st.query(j + 1, i) * 2 + i - j;
            // Add the new square sum to res
            res = (res + sum) % MOD;
            // Range update for index [j + 1, i]
            st.update(j + 1, i, 1);
            last[num] = i;
        }
        return (int) res;
    }

    private static class SegmentTree {

        private final int n;
        private final long[] tree;
        private final long[] mark;

        public SegmentTree(int n) {
            this.n = n;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n - 1));
            tree = new long[size];
            mark = new long[size];
        }

        public long query(int start, int end) {
            return query(1, 1, n, start, end);
        }

        public void update(int start, int end, long val) {
            update(1, 1, n, start, end, val);
        }

        private long query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            spread(node, left, right);
            int mid = left + (right - left) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end);
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            long lr = query(node * 2, left, mid, start, end);
            long rr = query(node * 2 + 1, mid + 1, right, start, end);
            return merge(lr, rr);
        }

        private void update(int node, int left, int right, int start, int end, long val) {
            if (left >= start && right <= end) {
                apply(node, left, right, val);
                return;
            }
            spread(node, left, right);
            int mid = left + (right - left) / 2;
            if (start <= mid) {
                update(node * 2, left, mid, start, end, val);
            }
            if (end > mid) {
                update(node * 2 + 1, mid + 1, right, start, end, val);
            }
            maintain(node);
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private long merge(long v1, long v2) {
            return v1 + v2;
        }

        private void spread(int node, int left, int right) {
            if (mark[node] == 0) {
                return;
            }
            int mid = left + (right - left) / 2;
            apply(node * 2, left, mid, mark[node]);
            apply(node * 2 + 1, mid + 1, right, mark[node]);
            mark[node] = 0;
        }

        private void apply(int node, int left, int right, long val) {
            tree[node] += val * (right - left + 1);
            mark[node] += val;
        }
    }
}
