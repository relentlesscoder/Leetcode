package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 10/09/2019.
 * #1214 https://leetcode.com/problems/two-sum-bsts/
 */
public class TwoSumBSTs {

	// time O(n + m), space O(1)
	public boolean twoSumBSTsTwoPointersWithMorrisTraversal(TreeNode root1, TreeNode root2, int target) {
		MorrisIterator itr1 = new MorrisIterator(root1);
		ReverseMorrisIterator itr2 = new ReverseMorrisIterator(root2);
		Integer v1 = itr1.next(), v2 = itr2.next();
		while (v1 != null && v2 != null) {
			int sum = v1.intValue() + v2.intValue();
			if (sum == target) {
				return true;
			} else if (sum < target) {
				v1 = itr1.next();
			} else {
				v2 = itr2.next();
			}
		}
		return false;
	}

	private static class MorrisIterator implements Iterator<Integer> {
		private TreeNode cur, pre;

		public MorrisIterator(TreeNode root) {
			cur = root;
			pre = null;
		}

		public boolean hasNext() {
			return cur != null;
		}

		// In-order traversal is left -> node -> right
		public Integer next() {
			Integer val = null;
			while (cur != null) {
				if (cur.left == null) {
					val = cur.val;
					cur = cur.right;
					break;
				} else {
					pre = cur.left;
					while (pre.right != null && pre.right != cur) {
						pre = pre.right;
					}
					if (pre.right == null) {
						pre.right = cur;
						cur = cur.left;
					} else {
						pre.right = null;
						val = cur.val;
						cur = cur.right;
						break;
					}
				}
			}
			return val;
		}
	}

	private static class ReverseMorrisIterator implements Iterator<Integer> {
		private TreeNode cur, pre;

		public ReverseMorrisIterator(TreeNode root) {
			cur = root;
			pre = null;
		}

		public boolean hasNext() {
			return cur != null;
		}

		// Reverse in-order traversal is right -> node -> left
		public Integer next() {
			Integer val = null;
			while (cur != null) {
				if (cur.right == null) {
					val = cur.val;
					cur = cur.left;
					break;
				} else {
					pre = cur.right;
					while (pre.left != null && pre.left != cur) {
						pre = pre.left;
					}
					if (pre.left == null) {
						pre.left = cur;
						cur = cur.right;
					} else {
						pre.left = null;
						val = cur.val;
						cur = cur.left;
						break;
					}
				}
			}
			return val;
		}
	}

	// time O(m + n), space O(m + n)
	public boolean twoSumBSTsTwoPointers(TreeNode root1, TreeNode root2, int target) {
		List<Integer> nums1 = new ArrayList<>();
		List<Integer> nums2 = new ArrayList<>();
		inOrder(root1, nums1);
		inOrder(root2, nums2);
		for (int i = 0, j = nums2.size() - 1; i < nums1.size() && j >= 0; ) {
			int sum = nums1.get(i) + nums2.get(j);
			if (sum == target) {
				return true;
			} else if (sum < target) {
				i++;
			} else {
				j--;
			}
		}
		return false;
	}

	private void inOrder(TreeNode node, List<Integer> vals) {
		if (node == null) {
			return;
		}
		inOrder(node.left, vals);
		vals.add(node.val);
		inOrder(node.right, vals);
	}

	// time O(n + m), space O(n)
	public boolean twoSumBSTsHashSet(TreeNode root1, TreeNode root2, int target) {
		Set<Integer> vals = new HashSet<>();
		dfs(root1, vals);
		return search(root2, target, vals);
	}

	private void dfs(TreeNode node, Set<Integer> vals) {
		if (node == null) {
			return;
		}
		vals.add(node.val);
		dfs(node.left, vals);
		dfs(node.right, vals);
	}

	private boolean search(TreeNode node, int target, Set<Integer> vals) {
		if (node == null) {
			return false;
		}
		if (vals.contains(target - node.val)) {
			return true;
		}
		return search(node.left, target, vals)
				|| search(node.right, target, vals);
	}

	// time O(n * log(m)), space O(log(n))
	public boolean twoSumBSTsBinarySearch(TreeNode root1, TreeNode root2, int target) {
		return inOrder(root1, root2, target);
	}

	private boolean search(int val, TreeNode root) {
		TreeNode node = root;
		while (node != null) {
			if (node.val == val) {
				return true;
			}
			if (node.val < val) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return false;
	}

	private boolean inOrder(TreeNode node1, TreeNode node2, int target) {
		if (node1 == null) {
			return false;
		}
		if (search(target - node1.val, node2)) {
			return true;
		}
		return inOrder(node1.left, node2, target)
				|| inOrder(node1.right, node2, target);
	}
}
