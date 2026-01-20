package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2019.
 * #0865 https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 */
public class SmallestSubtreeWithAllTheDeepestNodes {

	// time O(n), space O(h)
	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		// 同 #1123
		// 题目实际上是求所有最深节点的最低共同祖先，所以首先需要求得二叉树的深度然后将所有具有此
		// 深度的节点上浮，最后浮出水面的就是它们的共同祖先。
		// 递归二叉树得到二叉树的深度
		int depth = getDepth(root);
		// 再次递归二叉树得到所有最深节点的最低共同祖先
		return findCommonAncestor(root, depth, 0);
	}

	private TreeNode findCommonAncestor(TreeNode root, int depth, int curr) {
		if (root == null) {
			return null;
		}
		if (++curr == depth) { // 发现深度等于二叉树深度的节点，将其上浮
			return root;
		}
		TreeNode left = findCommonAncestor(root.left, depth, curr);
		TreeNode right = findCommonAncestor(root.right, depth, curr);
		// 左边为空则上浮右边
		if (left == null) {
			return right;
		}
		// 右边为空则上浮左边
		if (right == null) {
			return left;
		}
		// 如果两边都不为空，说明找到一个共同祖先节点则上浮此节点。
		return root;
	}

	private int getDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(getDepth(root.left), getDepth(root.right));
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
