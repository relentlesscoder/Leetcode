package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/13/2025.
 * #2589 https://leetcode.com/problems/minimum-time-to-complete-all-tasks/
 */
public class MinimumTimeToCompleteAllTasks {

    // time O(n * log(n) + n * log(m)), space O(m)
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int m = tasks[tasks.length - 1][1];
        SegmentTree st = new SegmentTree(m);
        for (int[] t : tasks) {
            int start = t[0], end = t[1], duration = t[2];
            st.toStart = duration - st.query(start, end); // Remove running time points
            if (st.toStart > 0) {
                st.update(start, end); // Add new time points
            }
        }
        return st.getAll();
    }

    private static class SegmentTree {

        private final int n;
        private final int[] tree;
        private final boolean[] mark;
        public int toStart;

        public SegmentTree(int n) {
            this.n = n;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
            tree = new int[size];
            mark = new boolean[size];
            toStart = 0;
        }

        public int getAll() {
            return tree[1];
        }

        public int query(int start, int end) {
            return query(1, 1, n, start, end);
        }

        public void update(int start, int end) {
            update(1, 1, n, start, end);
        }

        private void apply(int node, int left, int right) {
            tree[node] = right - left + 1;
            mark[node] = true;
        }

        private void spread(int node, int left, int right) {
            boolean toRun = mark[node];
            if (!toRun) {
                return;
            }
            int mid = (left + right) / 2;
            apply(node * 2, left, mid);
            apply(node * 2 + 1, mid + 1, right);
            mark[node] = false;
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private int merge(int v1, int v2) {
            return v1 + v2;
        }

        private int query(int node, int left, int right, int start, int end) {
            if (start <= left && right <= end) {
                return tree[node];
            }
            int mid = (left + right) / 2;
            spread(node, left, right);
            if (mid >= end) {
                return query(node * 2, left, mid, start, end);
            }
            if (mid < start) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            int lr = query(node * 2, left, mid, start, end);
            int rr = query(node * 2 + 1, mid + 1, right, start, end);
            return merge(lr, rr);
        }

        private void update(int node, int left, int right, int start, int end) {
            int size = right - left + 1;
            if (toStart == 0 || tree[node] == size) { // All nodes (time) are running
                return;
            }
            // Use if current range has any unoccupied time
            if (start <= left && right <= end && size - tree[node] <= toStart) {
                toStart -= size - tree[node];
                apply(node, left, right);
                return;
            }
            int mid = (left + right) / 2;
            spread(node, left, right);
            if (end > mid) { // Update right subtree first
                update(node * 2 + 1, mid + 1, right, start, end);
            }
            if (left <= mid) { // Update left subtree
                update(node * 2, left, mid, start, end);
            }
            maintain(node);
        }
    }
}
