package org.wshuai.leetcode;

import org.wshuai.algorithm.segmentTree.SegmentTreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 7/23/2017.
 * #315 https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountOfSmallerNumbersAfterSelf {
	//7ms
	public List<Integer> countSmaller(int[] nums) {
		//Note: use LinkedList instead of ArrayList to
		//improve the running time from 75ms to 7ms
		List<Integer> res = new LinkedList<Integer>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		int len = nums.length;
		int[] aux = new int[len];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int num : nums) {
			min = num < min ? num : min;
		}
		for (int i = 0; i < len; i++) {
			aux[i] = nums[i] - min + 1;
			max = aux[i] > max ? aux[i] : max;
		}
		int[] bit = new int[max + 1];
		for (int i = len - 1; i >= 0; i--) {
			res.add(0, sum(bit, aux[i] - 1));
			add(bit, aux[i]);
		}
		return res;
	}

	private void add(int[] bit, int idx) {
		for (int i = idx; i < bit.length - 1; i += i & (-i)) {
			bit[i]++;
		}
	}

	private int sum(int[] bit, int idx) {
		int sum = 0;
		for (int i = idx; i >= 1; i -= i & (-i)) {
			sum += bit[i];
		}
		return sum;
	}

	//Segment tree, 24ms
	public List<Integer> countSmallerSegmentTree(int[] nums) {
		List<Integer> res = new LinkedList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		int len = nums.length;
		int start = Integer.MAX_VALUE;
		int end = Integer.MIN_VALUE;
		for (int num : nums) {
			start = Math.min(num, start);
			end = Math.max(num, end);
		}
		SegmentTreeNode root = new SegmentTreeNode(start, end);
		for (int i = len - 1; i >= 0; i--) {
			int cnt = search(root, nums[i]);
			update(root, nums[i]);
			res.add(0, cnt);
		}
		return res;
	}

	private int search(SegmentTreeNode root, int val) {
		if (root == null) {
			return 0;
		}
		if (val > root.end) {
			return root.sum;
		}
		if (val < root.start) {
			return 0;
		}
		return search(root.left, val) + search(root.right, val);
	}

	private void update(SegmentTreeNode root, int val) {
		if (root.start == root.end && root.start == val) {
			root.sum++;
			return;
		}
		int mid = root.start + (root.end - root.start) / 2;
		if (val > mid) {
			if (root.right == null) {
				root.right = new SegmentTreeNode(mid + 1, root.end);
			}
			update(root.right, val);
		} else {
			if (root.left == null) {
				root.left = new SegmentTreeNode(root.start, mid);
			}
			update(root.left, val);
		}
		root.sum = (root.left == null ? 0 : root.left.sum) + (root.right == null ? 0 : root.right.sum);
	}
}
