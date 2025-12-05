package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/23/2020.
 * #1622 https://leetcode.com/problems/fancy-sequence/
 */
public class FancySequence {

    private class Fancy {

        private static final int MAX = 100_001;
        private final SegmentTree st;
        private int size;

        // time O(MAX), space O(MAX)
        public Fancy() {
            st = new SegmentTree(MAX);
            size = 0;
        }

        // time O(log(MAX)), space O(1)
        public void append(int val) {
            st.update(size, size, 1L, 1L * val);
            size++;
        }

        // time O(log(MAX)), space O(1)
        public void addAll(int inc) {
            st.update(0, size - 1, 1L, 1L * inc);
        }

        // time O(log(MAX)), space O(1)
        public void multAll(int m) {
            st.update(0, size - 1, 1L * m, 0L);
        }

        // time O(log(MAX)), space O(1)
        public int getIndex(int idx) {
            return idx >= size ? -1 : (int) st.query(idx, idx);
        }

        private static class SegmentTree {

            private static final int MOD = (int) 1e9 + 7;
            private final int n;
            private final long[] tree;
            private final long[] mul;
            private final long[] add;

            public SegmentTree(int n) {
                this.n = n;
                int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
                tree = new long[size];
                add = new long[size];
                mul = new long[size];
                Arrays.fill(mul, 1);
            }

            public void update(int start, int end, long mv, long av) {
                if (start > end) {
                    return;
                }
                update(1, 0, n - 1, start, end, mv, av);
            }

            public long query(int start, int end) {
                if (start > end) {
                    return -1;
                }
                return query(1, 0, n - 1, start, end);
            }

            private void update(int node, int left, int right, int start, int end, long mv, long av) {
                if (left >= start && right <= end) {
                    apply(node, left, right, mv, av);
                    return;
                }
                spread(node, left, right);
                int mid = left + (right - left) / 2;
                if (start <= mid) {
                    update(node * 2, left, mid, start, end, mv, av);
                }
                if (end > mid) {
                    update(node * 2 + 1, mid + 1, right, start, end, mv, av);
                }
                maintain(node);
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
                long leftRes = query(node * 2, left, mid, start, end);
                long rightRes = query(node * 2 + 1, mid + 1, right, start, end);
                return merge(leftRes, rightRes);
            }

            private void spread(int node, int left, int right) {
                // Check if apply is needed
                boolean toApply = mul[node] > 1 || add[node] > 0;
                if (!toApply) {
                    return;
                }
                // Call apply on child nodes
                int mid = left + (right - left) / 2;
                apply(node * 2, left, mid, mul[node], add[node]);
                apply(node * 2 + 1, mid + 1, right, mul[node], add[node]);
                // Reset apply flag
                mul[node] = 1;
                add[node] = 0;
            }

            private void apply(int node, int left, int right, long mv, long av) {
                if (mv > 1) {
                    tree[node] = tree[node] * mv % MOD;
                    mul[node] = mul[node] * mv % MOD;
                    add[node] = add[node] * mv % MOD;
                }
                if (av > 0) {
                    tree[node] = (tree[node] + (right - left + 1) * av) % MOD;
                    add[node] = (add[node] + av) % MOD;
                }
            }

            private void maintain(int node) {
                tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
            }

            private long merge(long a, long b) {
                return (a + b) % MOD;
            }
        }
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
