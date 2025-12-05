package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Wei on 11/14/2025.
 * #3569 https://leetcode.com/problems/maximize-count-of-distinct-primes-after-split/
 */
public class MaximizeCountOfDistinctPrimesAfterSplit {

    private static final int MAX = (int)1e5;
    private static final boolean[] NON_PRIME = new boolean[MAX + 1];

    static {
        NON_PRIME[0] = NON_PRIME[1] = true;
        for (int i = 2; i <= MAX; i++) {
            if (!NON_PRIME[i]) {
                for (int j = i; j <= MAX / i; j++) {
                    NON_PRIME[i * j] = true;
                }
            }
        }
    }

    // time O(n * log(n) + m * log(n)), space O(n)
    public int[] maximumCount(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) { // O(n)
            if (!NON_PRIME[nums[i]]) {
                map.computeIfAbsent(nums[i], k -> new TreeSet<>()).add(i);
            }
        }

        SegmentTree st = new SegmentTree(n);
        for (TreeSet<Integer> set : map.values()) { // O(n)
            if (set.size() > 1) {
                st.update(set.first(), set.last(), 1); // O(log(n))
            }
        }

        int[] res = new int[m];
        for (int i = 0; i < m; i++) { // O(m)
            int[] q = queries[i];
            int idx = q[0];
            int val = q[1];
            int old = nums[idx];
            nums[idx] = val;

            if (!NON_PRIME[old]) {
                TreeSet<Integer> set = map.get(old);
                if (set.size() > 1) {
                    st.update(set.first(), set.last(), -1); // O(log(n))
                }
                set.remove(idx);
                if (set.size() > 1) {
                    st.update(set.first(), set.last(), 1); // O(log(n))
                } else if (set.isEmpty()) {
                    map.remove(old);
                }
            }

            if (!NON_PRIME[val]) {
                TreeSet<Integer> set = map.computeIfAbsent(val, k -> new TreeSet<>());
                if (set.size() > 1) {
                    st.update(set.first(), set.last(), -1); // O(log(n))
                }
                set.add(idx);
                if (set.size() > 1) {
                    st.update(set.first(), set.last(), 1); // O(log(n))
                }
            }
            res[i] = map.size() + st.query(0, n - 1); // O(log(n))
        }
        return res;
    }

    private static class SegmentTree {

        private final int n;
        private final int[] tree;
        private final int[] mark;

        public SegmentTree(int n) {
            this.n = n;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n - 1));
            tree = new int[size];
            mark = new int[size];
        }

        public void update(int start, int end, int val) {
            update(1, 0, n - 1, start, end, val);
        }

        public int query(int start, int end) {
            return query(1, 0, n - 1, start, end);
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

        private int query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            spread(node);
            int mid = left + (right - left) / 2;
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

        private void spread(int node) {
            if (mark[node] == 0) {
                return;
            }
            apply(node * 2, mark[node]);
            apply(node * 2 + 1, mark[node]);
            mark[node] = 0;
        }

        private void apply(int node, int val) {
            tree[node] += val;
            mark[node] += val;
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private int merge(int v1, int v2) {
            return Math.max(v1, v2);
        }
    }
}
