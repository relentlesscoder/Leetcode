package org.wshuai.leetcode;

/**
 * Created by Wei on 4/3/17.
 * #501 https://leetcode.com/problems/find-mode-in-binary-search-tree/
 */
public class FindModeInBinarySearchTree {
	//O(1) space
	public int[] findMode(TreeNode root) {
		if (root == null) {
			return new int[0];
		}
		int min = findMin(root);
		//params[0] -> current value
		//params[1] -> max count
		//params[2] -> current count
		//params[3] -> number of nodes has same count
		//params[4] -> current index of result array
		int[] params = new int[5];
		params[0] = min;
		inorder(root, params, null, min);
		int[] res = new int[params[3]];
		params[0] = min;
		params[2] = 0;
		inorder(root, params, res, min);
		return res;
	}

	private int findMin(TreeNode root) {
		while (root.left != null) {
			root = root.left;
		}
		return root.val;
	}

	private void inorder(TreeNode root, int[] params, int[] result, int min) {
		TreeNode curr = root;
		TreeNode prev = null;
		while (curr != null) {
			if (curr.left == null) {
				handleValue(curr.val, params, result, min);
				curr = curr.right;
			} else {
				prev = curr.left;
				while (prev.right != null && prev.right != curr) {
					prev = prev.right;
				}
				if (prev.right != null) {
					handleValue(curr.val, params, result, min);
					prev.right = null;
					curr = curr.right;
				} else {
					prev.right = curr;
					curr = curr.left;
				}
			}
		}
	}

	private void handleValue(int curr, int[] params, int[] result, int min) {
		//params[0] -> current value
		//params[1] -> max count
		//params[2] -> current count
		//params[3] -> number of nodes has same count
		//params[4] -> current index of result array
		if (result != null) {
			int max = params[1];
			if (curr != params[0]) {
				params[0] = curr;
				params[2] = 1;
			} else {
				params[2]++;
			}
			if (params[2] == max) {
				result[params[4]] = curr;
				params[4]++;
			}
		} else {
			if (curr != params[0]) {
				params[0] = curr;
				params[2] = 1;
			} else {
				params[2]++;
			}
			if (params[2] == params[1]) {
				params[3]++;
			} else if (params[2] > params[1]) {
				params[1] = params[2];
				params[3] = 1;
			}
		}
	}
}
