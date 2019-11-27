package org.wshuai.leetcode;

import org.wshuai.algorithm.segmentTree.SegmentTreeNode;

/**
 * Created by Wei on 9/25/2016.
 * #307 https://leetcode.com/problems/range-sum-query-mutable/
 */
public class RangeSumQueryMutable {
	private int N;
	private SegmentTreeNode root;

	public RangeSumQueryMutable(int[] nums) {
		N = nums.length;
		root = buildSegmentTree(nums, 0, N - 1);
	}

	public void update(int i, int val) {
		updateUtil(root, i, val);
	}

	private void updateUtil(SegmentTreeNode root, int i, int val){
		if(root == null){
			return;
		}
		int mid = root.start + (root.end - root.start) / 2;
		if(i > mid){
			updateUtil(root.right, i, val);
		}else{
			updateUtil(root.left, i, val);
		}
		if(root.start == i && root.start == root.end){
			root.sum = val;
			return;
		}
		root.sum = root.left.sum + root.right.sum;
	}

	public int sumRange(int i, int j) {
		return sumRangeUtil(root, i, j);
	}

	private int sumRangeUtil(SegmentTreeNode root, int i, int j){
		if(root == null || i > root.end || j < root.start || i > j){
			return 0;
		}
		if(i <= root.start && j >= root.end){
			return root.sum;
		}
		int mid = root.start + (root.end - root.start) / 2;
		return sumRangeUtil(root.left, i, Math.min(mid, j)) +
			sumRangeUtil(root.right, Math.max(mid + 1, i), j);
	}

	private SegmentTreeNode buildSegmentTree(int[] nums, int i, int j){
		if(i > j){
			return null;
		}
		if(i == j){
			return new SegmentTreeNode(i, j, nums[i], null, null);
		}
		int mid = i + (j - i) / 2;
		SegmentTreeNode left = buildSegmentTree(nums, i, mid);
		SegmentTreeNode right = buildSegmentTree(nums, mid + 1, j);
		return new SegmentTreeNode(i, j, left.sum + right.sum, left, right);
	}
}
