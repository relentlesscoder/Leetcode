package org.wshuai.leetcode;

/**
 * Created by Wei on 04/03/2017.
 * #0501 https://leetcode.com/problems/find-mode-in-binary-search-tree/
 */
public class FindModeInBinarySearchTree {
	private int maxCount = 0;
	private int currentVal = Integer.MIN_VALUE;
	private int currentCount = 0;
	private int modCount = 0;
	private int[] res;

	// time O(n), space O(1)
	public int[] findMode(TreeNode root) {
		inorder(root);
		res = new int[modCount];
		currentCount = 0;
		modCount = 0;
		currentVal = Integer.MIN_VALUE;
		inorder(root);
		return res;
	}

	private void inorder(TreeNode root) {
		if (root == null) {
			return;
		}
		inorder(root.left);
		handleValue(root.val);
		inorder(root.right);
	}

	private void handleValue(int val) {
		if (val != currentVal) {
			currentVal = val;
			currentCount = 0;
		}
		currentCount++;
		if (currentCount > maxCount) {
			maxCount = currentCount;
			modCount = 1;
		} else if (currentCount == maxCount) {
			if (res != null) {
				res[modCount] = val;
			}
			modCount++;
		}
	}
}
