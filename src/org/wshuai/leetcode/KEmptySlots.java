package org.wshuai.leetcode;

import java.util.TreeSet;

/**
 * Created by Wei on 12/30/2019.
 * #0683 https://leetcode.com/problems/k-empty-slots/
 */
public class KEmptySlots {

    // time O(n * log(n)), space O(n)
    public int kEmptySlotsBinaryIndexedTree(int[] bulbs, int k) {
        int n = bulbs.length;
        BIT bit = new BIT(n);
        boolean[] on = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            int b = bulbs[i];
            if (b - k - 1 >= 1 && on[b - k - 1] && bit.pre(b) == bit.pre(b - k - 1)) {
                return i + 1;
            }
            if (b + k + 1 <= n && on[b + k + 1] && bit.pre(b) == bit.pre(b + k)) {
                return i + 1;
            }
            on[b] = true;
            bit.update(b, 1);
        }
        return -1;
    }

    private static class BIT {

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += (index & -index);
            }
        }

        public int pre(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= (index & -index);
            }
            return res;
        }
    }

    // time O(n * log(n)), space O(n)
    public int kEmptySlotsSegmentTree(int[] bulbs, int k) {
        int n = bulbs.length;
        SegmentTree st = new SegmentTree(n + 1);
        boolean[] on = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            int b = bulbs[i];
            if (b - k - 1 >= 1 && on[b - k - 1] && st.query(b - k, b - 1) == 0) {
                return i + 1;
            }
            if (b + k + 1 <= n && on[b + k + 1] && st.query(b + 1, b + k) == 0) {
                return i + 1;
            }
            on[b] = true;
            st.update(b, 1);
        }
        return -1;
    }

    private static class SegmentTree {

        private final int n;
        private final int[] tree;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        }

        public void update(int index, int val) {
            update(1, 1, n, index, val);
        }

        public int query(int start, int end) {
            if (start > end) {
                return 0;
            }
            return query(1, 1, n, start, end);
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
            int lr = query(node * 2, left, mid, start, end);
            int rr = query(node * 2 + 1, mid + 1, right, start, end);
            return merge(lr, rr);
        }

        private void update(int node, int left, int right, int index, int val) {
            if (left == right) {
                tree[node] += val;
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

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private int merge(int v1, int v2) {
            return v1 + v2;
        }
    }

    // time O(n * log(n)), space O(n)
    public int kEmptySlotsTreeSet(int[] bulbs, int K) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < bulbs.length; i++) {
            int cur = bulbs[i];
            Integer high = set.higher(cur), low = set.lower(cur);
            if (high != null && high.intValue() - cur - 1 == K) {
                return i + 1;
            }
            if (low != null && cur - low.intValue() - 1 == K) {
                return i + 1;
            }
            set.add(cur);
        }
        return -1;
    }
}
