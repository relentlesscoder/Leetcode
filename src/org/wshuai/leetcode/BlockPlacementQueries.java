package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Wei on 11/11/2025.
 * #3161 https://leetcode.com/problems/block-placement-queries/
 */
public class BlockPlacementQueries {

    // time O(n * log(m)), space O(m)
    public List<Boolean> getResultsSegmentTree(int[][] queries) {
        int m = 0;
        for (int[] q : queries) {
            m = Math.max(m, q[1]);
        }
        m++;
        SegmentTree st = new SegmentTree(m);
        TreeSet<Integer> set = new TreeSet<>(List.of(0, m));
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) { // O(n)
            int x = q[1];
            int pre = set.floor(x - 1); // Find previous blocker
            if (q[0] == 1) {
                int next = set.ceiling(x); // Find next blocker (or self)
                set.add(x);
                // Divide range [pre, next] into two ranges
                // [pre, x] and [x, next].
                st.update(1, 0, m, x, x - pre);
                st.update(1, 0, m, next, next - x);
            } else {
                // Find the max gap g in range [0,pre], the result for the
                // query is max(g, x - pre), x - pre is the last range [pre, x]
                // formed by the queried index x and its previous blocker pre.
                int maxGap = Math.max(st.query(1, 0, m, pre), x - pre);
                res.add(maxGap >= q[2]);
            }
        }
        return res;
    }

    private static class SegmentTree {

        private final int[] tree;

        public SegmentTree(int n) {
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
            tree = new int[size];
        }

        public void update(int node, int left, int right, int index, int val) {
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
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        public int query(int node, int left, int right, int end) {
            if (right <= end) {
                return tree[node];
            }
            int mid = (left + right) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, end);
            }
            int rightRes = query(node * 2 + 1, mid + 1, right, end);
            return Math.max(tree[node * 2], rightRes);
        }
    }

    // time O(n * log(m)), space O(m)
    public List<Boolean> getResultsBinaryIndexedTree(int[][] queries) {
        int n = queries.length, m = 0;
        List<Integer> bcks = new ArrayList<>();
        bcks.add(0);
        for (int[] q : queries) {
            m = Math.max(m, q[1]);
            if (q[0] == 1) {
                bcks.add(q[1]);
            }
        }
        m++;
        Collections.sort(bcks);
        TreeSet<Integer> set = new TreeSet<>(bcks);
        set.add(m);
        BIT bit = new BIT(m);
        for (int i = 1; i < bcks.size(); i++) {
            bit.update(bcks.get(i), bcks.get(i) - bcks.get(i - 1));
        }
        List<Boolean> res = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            int[] q = queries[i];
            int x = q[1];
            int pre = set.floor(x - 1); // Find previous blocker
            if (q[0] == 1) {
                set.remove(x);
                int next = set.ceiling(x); // Find next blocker (or self)
                // Divide range [pre, next] into two ranges
                // [pre, x] and [x, next].
                bit.update(next, next - pre);
            } else {
                // Find the max gap g in range [0,pre], the result for the
                // query is max(g, x - pre), x - pre is the last range [pre, x]
                // formed by the queried index x and its previous blocker pre.
                int maxGap = Math.max(bit.query(pre), x - pre);
                res.add(maxGap >= q[2]);
            }
        }
        Collections.reverse(res);
        return res;
    }

    private static class BIT {

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] = Math.max(tree[index], val);
                index += lowbit(index);
            }
        }

        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res = Math.max(tree[index], res);
                index -= lowbit(index);
            }
            return res;
        }

        private int lowbit(int index) {
            return index & -index;
        }
    }
}
