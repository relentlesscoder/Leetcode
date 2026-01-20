package org.wshuai.leetcode;

/**
 * Created by Wei on 01/18/2020.
 * #1325 https://leetcode.com/problems/delete-leaves-with-a-given-value/
 */
public class DeleteLeavesWithAGivenValue {

    // time O(n), space O(h)
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
		// 先递归左右子树以删除当前节点以下的叶子节点
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
		// 节点可被删除的条件:
		//   1. 节点的值等于 target
		//   2. 左子树为空或者已经被删除
		//   3. 右子树为空或者已经被删除
        if (root.val == target && root.left == null && root.right == null) {
            return null;
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
