package org.wshuai.leetcode;

/**
 * Created by Wei on 11/27/2019.
 * #0732 https://leetcode.com/problems/my-calendar-iii/
 */
public class MyCalendarIII {

	// time O(q * log(MAX)), space O(q * log(MAX))
	class MyCalendarThree {

		private final SegmentTree st;

		public MyCalendarThree() {
			st = new SegmentTree();
		}

		public int book(int startTime, int endTime) {
			st.update(startTime, endTime - 1, 1);
			return st.queryMax();
		}

		private class SegmentTree {

			private static final int MAX = (int) 1e9 + 1;
			private final Node root;

			public SegmentTree() {
				root = new Node();
			}

			public int queryMax() {
				return root.val;
			}

			public int query(int start, int end) {
				return query(root, 0, MAX - 1, start, end);
			}

			public void update(int start, int end, int val) {
				update(root, 0, MAX - 1, start, end, val);
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
					node.val += val;
					node.mark += val;
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
				node.left.val += node.mark;
				node.right.val += node.mark;
				node.left.mark += node.mark;
				node.right.mark += node.mark;
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
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
