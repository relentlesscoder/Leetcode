package org.wshuai.leetcode;

/**
 * Created by Wei on 11/04/2025.
 * #3165 https://leetcode.com/problems/maximum-sum-of-subsequence-with-non-adjacent-elements/
 */
public class MaximumSumOfSubsequenceWithNonAdjacentElements {

    private static final int MOD = (int)1e9 + 7;

    // time O(n + q * log(n)), space O(n)
    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        // https://leetcode.cn/problems/maximum-sum-of-subsequence-with-non-adjacent-elements/solutions/2790603/fen-zhi-si-xiang-xian-duan-shu-pythonjav-xnhz/
        SegmentTree st = new SegmentTree(nums);
        long res = 0;
        for (int[] query : queries) {
            st.update(query[0], query[1]);
            res += st.get();
        }
        return (int) (res % MOD);
    }

    private static class SegmentTree {

        private int n;

        private long[][] tree;

        public SegmentTree(int[] nums) {
            n = nums.length;
            tree = new long[4 * n][4];
            build(nums, 1, 0, n - 1);
        }

        public long get() {
            return tree[1][3];
        }

        public void update(int i, int val) {
            update(1, 0, n - 1, i, val);
        }

        private void update(int node, int l, int r, int i, int val) {
            if (l == r) {
                tree[node][3] = Math.max(val, 0);
                return;
            }
            int m = (l + r) / 2;
            if (i <= m) {
                update(node * 2, l, m, i, val);
            } else {
                update(node * 2 + 1, m + 1, r, i, val);
            }
            maintain(node);
        }

        private void build(int[] nums, int node, int l, int r) {
            if (l == r) {
                tree[node][3] = Math.max(nums[l], 0);
                return;
            }
            int m = (l + r) / 2;
            build(nums, node * 2, l, m);
            build(nums, node * 2 + 1, m + 1, r);
            maintain(node);
        }

        private void maintain(int node) {
            long[] a = tree[node * 2], b = tree[node * 2 + 1];
            tree[node][0] = Math.max(a[0] + b[2], a[1] + b[0]);
            tree[node][1] = Math.max(a[0] + b[3], a[1] + b[1]);
            tree[node][2] = Math.max(a[2] + b[2], a[3] + b[0]);
            tree[node][3] = Math.max(a[2] + b[3], a[3] + b[1]);
        }
    }
}
