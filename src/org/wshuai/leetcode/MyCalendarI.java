package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2019.
 * #0729 https://leetcode.com/problems/my-calendar-i/
 */
public class MyCalendarI {

    // time O(q * log(MAX)), space O(q * log(MAX))
    private class MyCalendar {

        private final SegmentTree st;

        public MyCalendar() {
            st = new SegmentTree();
        }

        public boolean book(int startTime, int endTime) {
            if (!st.query(startTime, endTime - 1)) {
                st.update(startTime, endTime - 1, true);
                return true;
            }
            return false;
        }

        private static class SegmentTree {

            private static final int MAX = (int) 1e9 + 1;
            private Node root;

            public SegmentTree() {
                root = new Node();
            }

            public void update(int start, int end, boolean val) {
                update(root, 0, MAX - 1, start, end, val);
            }

            public boolean query(int start, int end) {
                return query(root, 0, MAX - 1, start, end);
            }

            private void update(Node node, int left, int right, int start, int end, boolean val) {
                if (left >= start && right <= end) {
                    node.val = val;
                    node.mark = true;
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

            private boolean query(Node node, int left, int right, int start, int end) {
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
                boolean lr = query(node.left, left, mid, start, end);
                boolean rr = query(node.right, mid + 1, right, start, end);
                return lr || rr;
            }

            private void spread(Node node) {
                if (node.left == null) {
                    node.left = new Node();
                }
                if (node.right == null) {
                    node.right = new Node();
                }
                if (!node.mark) {
                    return;
                }
                node.left.val = node.val;
                node.right.val = node.val;
                node.left.mark = true;
                node.right.mark = true;
                node.mark = false;
            }

            private void maintain(Node node) {
                node.val = node.left.val || node.right.val;
            }

            private static class Node {
                Node left;
                Node right;
                boolean val;
                boolean mark;

                public Node() {
                    val = false;
                    mark = false;
                }
            }
        }
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
