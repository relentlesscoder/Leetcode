package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 08/07/2019.
 * #0938 https://leetcode.com/problems/range-sum-of-bst/
 */
public class RangeSumOfBST {

	// time O(n), space O(n)
	public int rangeSumBST(TreeNode root, int low, int high) {
		if (root == null) {
			return 0;
		}
		// 当前值小于 low ，递归右子树
		if (root.val < low) {
			return rangeSumBST(root.right, low, high);
		} else if (root.val > high) { // 当前值大于 high ，递归左子树
			return rangeSumBST(root.left, low, high);
		}
		// 当前值满足 low <= val <= high, 则返回当前值与左右子树递归的和。
		return root.val + rangeSumBST(root.right, low, high) + rangeSumBST(root.left, low, high);
	}

	// time O(n), space O(1)
	public int rangeSumBSTMorris(TreeNode root, int low, int high) {
		int res = 0;
		TreeNode curr = root;
		while (curr != null) {
			if (curr.left == null) {
				if (low <= curr.val && curr.val <= high) {
					res += curr.val;
				}
				if (curr.val > high) {
					break;
				}
				curr = curr.right;
			} else {
				TreeNode node = curr.left;
				while (node.right != null && node.right != curr) {
					node = node.right;
				}
				if (node.right == null) {
					node.right = curr;
					curr = curr.left;
				} else {
					node.right = null;
					if (low <= curr.val && curr.val <= high) {
						res += curr.val;
					}
					if (curr.val > high) {
						break;
					}
					curr = curr.right;
				}
			}
		}
		return res;
	}

	/**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
