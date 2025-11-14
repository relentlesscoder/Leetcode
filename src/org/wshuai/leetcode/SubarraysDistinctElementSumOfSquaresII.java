package org.wshuai.leetcode;

/**
 * Created by Wei on 11/14/2025.
 * #2916 https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/
 */
public class SubarraysDistinctElementSumOfSquaresII {

    private static final int MOD = (int)1e9 + 7;

    // time O(n * log(n)), space O(n)
    public int sumCounts(int[] nums) {
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
            sum += st.query(j + 1, i) * 2 + i - j;
            res = (res + sum) % MOD;
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
