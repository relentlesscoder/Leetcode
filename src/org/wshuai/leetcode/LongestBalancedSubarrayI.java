package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/14/2025.
 * #3719 https://leetcode.com/problems/longest-balanced-subarray-i/
 */
public class LongestBalancedSubarrayI {

    // time O(n * log(n)), space O(n)
    public int longestBalanced(int[] nums) {
        int res = 0, n = nums.length, max = -1, sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] last = new int[max + 1];
        Arrays.fill(last, -1);
        SegmentTree st = new SegmentTree(n + 1);
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            int val = (num & 1) == 1 ? 1 : -1;
            if (last[num] == -1) {
                sum += val; // Only add val to sum at first meet
                st.update(i, n, val);
            } else {
                st.update(last[num], i - 1, -val);
            }
            last[num] = i;
            int idx = st.findFirst(0, i - 1, sum);
            if (idx >= 0) {
                res = Math.max(res, i - idx);
            }
        }
        return res;
    }

    private static class SegmentTree {

        private final int n;
        private final int[][] tree;
        private final int[] mark;

        public SegmentTree(int n) {
            this.n = n;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n - 1));
            // tree[i][0] is min, tree[i][1] is max
            tree = new int[size][2];
            mark = new int[size];
        }

        public void update(int start, int end, int val) {
            update(1, 0, n - 1, start, end, val);
        }

        public int findFirst(int start, int end, int target) {
            return findFirst(1, 0, n - 1, start, end, target);
        }

        private int findFirst(int node, int left, int right, int start, int end, int target) {
            if (left > right || right < start || tree[node][0] > target || tree[node][1] < target) {
                return -1;
            }
            if (left == right) {
                return left;
            }
            spread(node);
            int mid = left + (right - left) / 2;
            int res = findFirst(node * 2, left, mid, start, end, target);
            if (res == -1) {
                res = findFirst(node * 2 + 1, mid + 1, right, start, end, target);
            }
            return res;
        }

        private void update(int node, int left, int right, int start, int end, int val) {
            if (left >= start && right <= end) {
                apply(node, val);
                return;
            }
            spread(node);
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
            tree[node][0] = Math.min(tree[node * 2][0], tree[node * 2 + 1][0]);
            tree[node][1] = Math.max(tree[node * 2][1], tree[node * 2 + 1][1]);
        }

        private void apply(int node, int val) {
            tree[node][0] += val;
            tree[node][1] += val;
            mark[node] += val;
        }

        private void spread(int node) {
            int val = mark[node];
            if (val == 0) {
                return;
            }
            apply(node * 2, val);
            apply(node * 2 + 1, val);
            mark[node] = 0;
        }
    }
}
