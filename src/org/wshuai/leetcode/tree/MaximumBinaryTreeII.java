package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 10/14/2019.
 * #0998 https://leetcode.com/problems/maximum-binary-tree-ii/
 */
public class MaximumBinaryTreeII {

	// time O(n), space O(1)
	public TreeNode insertIntoMaxTree(TreeNode root, int val) {
		TreeNode curr = root, // 当前节点
				parent = null, // 父节点
				node = new TreeNode(val); // 待插入节点
		// 因为是最大树所以我们从根结点开始找到第一个小于 val 的节点
		while (curr != null && curr.val > val) {
			parent = curr;
			curr = curr.right;
		}
		// 两种情况:
		//   1. parent == null 说明根结点就小于待插入节点则直接把
		//   根结点设为待插入节点的左子节点返回待插入节点作为新的根结
		//   点。
		//   2. 将待插入节点设为父节点的右子树并且将父节点的右子树设
		//   为待插入节点的左子树。
		if (parent == null) {
			node.left = root;
			return node;
		} else {
			node.left = parent.right;
			parent.right = node;
			return root;
		}
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
