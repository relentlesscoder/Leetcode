package org.wshuai.leetcode;

/**
 * Created by Wei on 11/04/2025.
 * #3479 https://leetcode.com/problems/fruits-into-baskets-iii/
 */
public class FruitsIntoBasketsIII {

    // time O(n * log(n)), space O(n)
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int res = fruits.length, m = baskets.length;
        SegmentTree st = new SegmentTree(baskets);
        for (int fruit : fruits) {
            int index = st.findFirst(1, 0, m - 1, fruit);
            if (index >= 0) {
                res--;
                st.update(index, -1);
            }
        }
        return res;
    }

    private static class SegmentTree {

        private final int n;

        private final int[] tree;

        public SegmentTree(int[] nums) {
            n = nums.length;
            tree = new int[4 * n];
            build(nums, 1, 0, n - 1);
        }

        public int findFirst(int node, int l, int r, int val) {
            if (tree[node] < val) {
                return -1;
            }
            if (l == r) {
                return l;
            }
            int m = (l + r) / 2;
            int res = findFirst(node * 2, l, m, val);
            if (res == -1) {
                res = findFirst(node * 2 + 1, m + 1, r, val);
            }
            return res;
        }

        /*public int query(int start, int end) {
            return query(1, 0, n - 1, start, end);
        }*/

        public void update(int i, int val) {
            update(1, 0, n - 1, i, val);
        }

        private void build(int[] nums, int node, int l, int r) {
            if (l == r) {
                tree[node] = nums[l];
                return;
            }
            int m = (l + r) / 2;
            build(nums, node * 2, l, m);
            build(nums, node * 2 + 1, m + 1, r);
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        /*private int query(int node, int l, int r, int s, int e) {
            if (l >= s && r <= e) {
                return tree[node];
            }
            int m = (l + r) / 2;
            if (e <= m) {
                return query(node * 2, l, m, s, e);
            }
            if (s > m) {
                return query(node * 2 + 1, m + 1, r, s, e);
            }
            int lr = query(node * 2, l, m, s, e);
            int rr = query(node * 2 + 1, m + 1, r, s, e);
            return Math.max(lr, rr);
        }*/

        private void update(int node, int l, int r, int i, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }
            int m = (l + r) / 2;
            if (i <= m) {
                update(node * 2, l, m, i, val);
            } else {
                update(node * 2 + 1, m + 1, r, i, val);
            }
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }
}
