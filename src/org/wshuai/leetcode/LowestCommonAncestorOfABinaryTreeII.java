package org.wshuai.leetcode;

/**
 * Created by Wei on 11/09/2020.
 * #1644 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 */
public class LowestCommonAncestorOfABinaryTreeII {

	private boolean foundP = false;
	private boolean foundQ = false;

	// time O(n), space O(n)
	public TreeNode lowestCommonAncestorSinglePass(TreeNode root, TreeNode p, TreeNode q) {
		foundP = false;
		foundQ = false;
		TreeNode lca = lowestCommonAncestor(root, p.val, q.val);
		return foundP && foundQ ? lca : null;
	}

	private TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
		if (root == null) {
			return root;
		}
		// 先递归再判断节点是否为 p 或者 q，因为我们需要遍历所有节点确定 p 和 q 是否
		// 都存在。
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (root.val == p) {
			foundP = true;
			return root;
		}
		if (root.val == q) {
			foundQ = true;
			return root;
		}
		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		return root;
	}

	// time O(n), space O(n)
	public TreeNode lowestCommonAncestorMultiPass(TreeNode root, TreeNode p, TreeNode q) {
		// 判断 p 和 q 是否存在于树中
		if (!findNode(root, p.val) || !findNode(root, q.val)) {
			return null;
		}
		// 如果都存在，返回它们的 LCA
		return lowestCommonAncestorMultiPass(root, p.val, q.val);
	}

	private boolean findNode(TreeNode node, int val) {
		if (node == null) {
			return false;
		}
		// 找到目标节点
		if (node.val == val) {
			return true;
		}
		boolean res = false;
		// 递归左子树
		if (findNode(node.left, val)) {
			res = true;
		} else if (findNode(node.right, val)) { // // 递归右子树
			res = true;
		}
		return res;
	}

	private TreeNode lowestCommonAncestorMultiPass(TreeNode root, int p, int q) {
		if (root == null || root.val == p || root.val == q) {
			return root;
		}
		TreeNode left = lowestCommonAncestorMultiPass(root.left, p, q);
		TreeNode right = lowestCommonAncestorMultiPass(root.right, p, q);
		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		return root;
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
