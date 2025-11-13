package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/2025.
 * #2547 https://leetcode.com/problems/minimum-cost-to-split-an-array/
 */
public class MinimumCostToSplitAnArray {

    // time O(n * log(n)), space O(n)
    public int minCost(int[] nums, int k) {
        int n = nums.length, res = 0;
        SegmentTree st = new SegmentTree(n);
        int[] last = new int[n], prevToLast = new int[n];
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            // Update f[i - 1] at index i
            st.update(i, i, res);
            // If we add num x at index i, it has impact on at most
            // two ranges:
            //   1. [last[num] + 1, i] - it has one more unique number
            //      so f[j] - unique in this range need to minus 1
            //   2. [prevToLast[num] + 1, last[num]] - it has one less
            //      unique number so f[j] - unique need to add 1
            // Ranges before prevToLast[num] f[j] - unique are not impacted
            // since they all has more than 2 num
            st.update(last[num] + 1, i, -1);
            if (last[num] > 0) {
                st.update(prevToLast[num] + 1, last[num], 1);
            }
            // res is f[i] = k + min(f[j] - unique)
            res = k + st.query(1, i);
            prevToLast[num] = last[num];
            last[num] = i;
        }
        return res + n;
    }

    private static class SegmentTree {

        private final int n;
        private final int[] tree;
        private final int[] mark;

        public SegmentTree(int n) {
            this.n = n;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
            tree = new int[size];
            mark = new int[size];
        }

        public int query(int start, int end) {
            return query(1, 1, n, start, end);
        }

        public void update(int start, int end, int val) {
            update(1, 1, n, start, end, val);
        }

        private void update(int node, int left, int right, int start, int end, int val) {
            if (left >= start && right <= end) {
                apply(node, val);
                return;
            }
            spread(node);
            int mid = (left + right) / 2;
            if (start <= mid) {
                update(node * 2, left, mid, start, end, val);
            }
            if (end > mid) {
                update(node * 2 + 1, mid + 1, right, start, end, val);
            }
            maintain(node);
        }

        private int query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            spread(node);
            int mid = (left + right) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end);
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            int lr = query(node * 2, left, mid, start, end);
            int rr = query(node * 2 + 1, mid + 1, right, start, end);
            return merge(lr, rr);
        }

        private void apply(int node, int val) {
            tree[node] += val;
            mark[node] += val;
        }

        private void spread(int node) {
            if (mark[node] == 0) {
                return;
            }
            apply(node * 2, mark[node]);
            apply(node * 2 + 1, mark[node]);
            mark[node] = 0;
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private int merge(int v1, int v2) {
            return Math.min(v1, v2);
        }
    }

    // time O(n^2), space O(n)
    public int minCostDP(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int[] freq = new int[n];
            int unique = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (freq[nums[j]] == 0) {
                    freq[nums[j]] = 1;
                    unique++;
                } else if (freq[nums[j]] == 1) {
                    freq[nums[j]] = 2;
                    unique--;
                }
                min = Math.min(min, dp[j] - unique);
            }
            dp[i] = k + min;
        }
        return dp[n] + n;
    }
}
