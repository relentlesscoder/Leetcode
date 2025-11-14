package org.wshuai.leetcode;

/**
 * Created by Wei on 11/14/2025.
 * #2276 https://leetcode.com/problems/count-integers-in-intervals/
 */
public class CountIntegersInIntervals {

    // time O(q * log(MAX)), space O(q * log(MAX))
    private class CountIntervals {

        private final SegmentTree st;

        public CountIntervals() {
            st = new SegmentTree();
        }

        public void add(int left, int right) {
            st.update(left, right);
        }

        public int count() {
            return st.queryMax();
        }

        private class SegmentTree {

            private static final int MAX = (int) 1e9 + 1;
            private final Node root;

            public SegmentTree() {
                root = new Node();
            }

            public int queryMax() {
                return root.sum;
            }

            public int query(int start, int end) {
                return query(root, 1, MAX - 1, start, end);
            }

            public void update(int start, int end) {
                update(root, 1, MAX - 1, start, end);
            }

            private int query(Node node, int left, int right, int start, int end) {
                if (left >= start && right <= end) {
                    return node.sum;
                }
                spread(node, left, right);
                int mid = left + (right - left) / 2;
                if (end <= mid) {
                    return query(node.left, left, mid, start, end);
                }
                if (start > mid) {
                    return query(node.right, mid + 1, right, start, end);
                }
                int lr = query(node.left, left, mid, start, end);
                int rr = query(node.right, mid + 1, right, start, end);
                return lr + rr;
            }

            private void update(Node node, int left, int right, int start, int end) {
                if (left >= start && right <= end) {
                    node.sum = right - left + 1;
                    node.mark = 1;
                    return;
                }
                spread(node, left, right);
                int mid = left + (right - left) / 2;
                if (start <= mid) {
                    update(node.left, left, mid, start, end);
                }
                if (end > mid) {
                    update(node.right, mid + 1, right, start, end);
                }
                maintain(node);
            }

            private void spread(Node node, int left, int right) {
                if (node.left == null) {
                    node.left = new Node();
                }
                if (node.right == null) {
                    node.right = new Node();
                }
                if (node.mark == 0) {
                    return;
                }
                int mid = left + (right - left) / 2;
                node.left.sum = mid - left + 1;
                node.right.sum = right - mid;
                node.left.mark = 1;
                node.right.mark = 1;
                node.mark = 0;
            }

            private void maintain(Node node) {
                node.sum = node.left.sum + node.right.sum;
            }

            private class Node {
                Node left;
                Node right;
                int sum;
                int mark;
            }
        }
    }
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals obj = new CountIntervals();
 * obj.add(left,right);
 * int param_2 = obj.count();
 */
