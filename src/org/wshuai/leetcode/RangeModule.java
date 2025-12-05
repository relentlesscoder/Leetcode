package org.wshuai.leetcode;

/**
 * Created by Wei on 11/27/2019.
 * #0715 https://leetcode.com/problems/range-module/
 */
public class RangeModule {

	// time O(n * log(n)), space O(n * log(n))
	private class RangeModuleSegmentTree {

		private static final int MAX_RANGE = (int)1e9 + 7;
		private final SegmentTree st;

		public RangeModuleSegmentTree() {
			st = new SegmentTree(MAX_RANGE);
		}

		public void addRange(int left, int right) {
			st.update(left, right - 1, true);
		}

		public boolean queryRange(int left, int right) {
			return st.query(left, right - 1);
		}

		public void removeRange(int left, int right) {
			st.update(left, right - 1, false);
		}

		private static class SegmentTree {

			private final int MAX;
			private Node root;

			public SegmentTree(int max) {
				this.MAX = max;
				root = new Node();
			}

			public void update(int start, int end, boolean val) {
				update(root, 1, MAX, start, end, val);
			}

			public boolean query(int start, int end) {
				return query(root, 1, MAX, start, end);
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
				return lr && rr;
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
				node.val = node.left.val && node.right.val;
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
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
