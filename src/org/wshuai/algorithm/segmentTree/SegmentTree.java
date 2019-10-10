package org.wshuai.algorithm.segmentTree;

/**
 * Created by Wei on 9/25/2016.
 * Binary tree representation
 */
public class SegmentTree {
	private SegmentTreeNode root = null;
	private int count;

	public SegmentTree(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		count = nums.length;
		this.root = buildSegmentTree(nums, 0, nums.length - 1);
	}

	void update(int i, int val) {
		if (root == null) {
			return;
		}

		if (validateIndex(i)) {
			updateHelper(root, i, val);
		}
	}

	public int sumRange(int i, int j) {
		if (root == null) {
			return 0;
		}

		if (validateIndex(i) && validateIndex(j)) {
			return sumRangeHelper(root, i, j);
		}

		return 0;
	}

	private void updateHelper(SegmentTreeNode root, int i, int val) {
		if (root == null) {
			return;
		}

		int mid = root.start + (root.end - root.start) / 2;
		if (i > mid) {
			updateHelper(root.right, i, val);
		} else {
			updateHelper(root.left, i, val);
		}

		if (root.start == root.end && root.start == i) {
			root.sum = val;
			return;
		}

		root.sum = root.left.sum + root.right.sum;
	}

	private int sumRangeHelper(SegmentTreeNode root, int i, int j) {
		if (root == null || i > root.end || j < root.start || i > j) {
			return 0;
		}

		if (i <= root.start && j >= root.end) {
			return root.sum;
		}

		int mid = root.start + (root.end - root.start) / 2;
		int result = sumRangeHelper(root.left, i, Math.min(mid, j)) +
				sumRangeHelper(root.right, Math.max(mid + 1, i), j);
		return result;
	}

	private SegmentTreeNode buildSegmentTree(int[] nums, int i, int j) {
		if (i > j) {
			return null;
		}

		if (i == j) {
			return new SegmentTreeNode(i, j, nums[i]);
		}

		SegmentTreeNode current = new SegmentTreeNode(i, j);

		int mid = i + (j - i) / 2;

		current.left = buildSegmentTree(nums, i, mid);
		current.right = buildSegmentTree(nums, mid + 1, j);

		current.sum = current.left.sum + current.right.sum;

		return current;
	}

	private boolean validateIndex(int i) {
		return i >= 0 && i < count;
	}
}
