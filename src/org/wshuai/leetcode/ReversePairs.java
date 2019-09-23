package org.wshuai.leetcode;

/**
 * Created by Wei on 7/23/17.
 * #493 https://leetcode.com/problems/reverse-pairs/
 */
public class ReversePairs {
	//Segment tree, 183ms
	public int reversePairs(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		long max = Long.MIN_VALUE;
		long min = Long.MAX_VALUE;
		int i = 0;
		for (int num : nums) {
			long prod = 2 * ((long) num);
			max = Math.max(prod, max);
			min = Math.min(prod, min);
		}
		SegmentTreeNodeLong root = new SegmentTreeNodeLong(min, max);
		int res = 0;
		for (i = len - 1; i >= 0; i--) {
			long val = (long) nums[i];
			res += search(root, val);
			add(root, 2 * val);
		}
		return res;
	}

	private void add(SegmentTreeNodeLong node, long val) {
		if (node.start == node.end && node.start == val) {
			node.sum++;
			return;
		}
		long mid = node.start + (node.end - node.start) / 2;
		if (val <= mid) {
			if (node.left == null) {
				node.left = new SegmentTreeNodeLong(node.start, mid);
			}
			add(node.left, val);
		} else {
			if (node.right == null) {
				node.right = new SegmentTreeNodeLong(mid + 1, node.end);
			}
			add(node.right, val);
		}
		node.sum = (node.left == null ? 0 : node.left.sum) + (node.right == null ? 0 : node.right.sum);
	}

	private int search(SegmentTreeNodeLong node, long val) {
		if (node == null) {
			return 0;
		}
		if (val < node.start) {
			return 0;
		}
		if (val > node.end) {
			return node.sum;
		}
		return search(node.left, val) + search(node.right, val);
	}
}

class SegmentTreeNodeLong {
	long start;
	long end;
	int sum;
	SegmentTreeNodeLong left;
	SegmentTreeNodeLong right;

	public SegmentTreeNodeLong(long start, long end) {
		this.start = start;
		this.end = end;
		this.sum = 0;
	}
}