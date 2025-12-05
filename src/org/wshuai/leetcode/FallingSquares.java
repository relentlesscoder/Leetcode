package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/26/2019.
 * #0699 https://leetcode.com/problems/falling-squares/
 */
public class FallingSquares {

    // time O(n * log(MAX)), space O(n * log(MAX))
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        SegmentTree st = new SegmentTree();
        for (int[] p : positions) {
            int height = st.query(p[0], p[0] + p[1] - 1);
            st.update(p[0], p[0] + p[1] - 1, height + p[1]);
            res.add(st.getMax());
        }
        return res;
    }

    private class SegmentTree {

        private static final int MAX = (int) 1e8 + 1;
        private final Node root;

        public SegmentTree() {
            root = new Node();
        }

        public int getMax() {
            return root.val;
        }

        public int query(int start, int end) {
            return query(root, 1, MAX, start, end);
        }

        public void update(int start, int end, int val) {
            update(root, 1, MAX, start, end, val);
        }

        private int query(Node node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return node.val;
            }
            spread(node);
            int mid = left + (right - left) / 2;
            if (end <= mid) {
                return query(node.left, left, mid, start, end);
            }
            if (start > mid) {
                return query(node.right, mid + 1, right, start, end);
            }
            int lr = query(node.left, left, mid, start, end);
            int rr = query(node.right, mid + 1, right, start, end);
            return Math.max(lr, rr);
        }

        private void update(Node node, int left, int right, int start, int end, int val) {
            if (left >= start && right <= end) {
                node.val = val;
                node.mark = val;
                return;
            }
            spread(node);
            int mid = left + (right - left) / 2;
            if (start <= mid) {
                update(node.left, left, mid, start, end, val);
            }
            if (end > mid) {
                update(node.right, mid + 1, right, start, end, val);
            }
            maintain(node);
        }

        private void spread(Node node) {
            if (node.left == null) {
                node.left = new Node();
            }
            if (node.right == null) {
                node.right = new Node();
            }
            if (node.mark == 0) {
                return;
            }
            node.left.val = node.val;
            node.right.val = node.val;
            node.left.mark = node.mark;
            node.right.mark = node.mark;
            node.mark = 0;
        }

        private void maintain(Node node) {
            node.val = Math.max(node.left.val, node.right.val);
        }

        private class Node {
            Node left;
            Node right;
            int val;
            int mark;
        }
    }
}
