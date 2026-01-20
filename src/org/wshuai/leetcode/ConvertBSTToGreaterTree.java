package org.wshuai.leetcode;

/**
 * Created by Wei on 07/20/2017.
 * #0538 https://leetcode.com/problems/convert-bst-to-greater-tree/
 */
public class ConvertBSTToGreaterTree {

	// time O(n), space O(h)
	public TreeNode convertBST(TreeNode root) {
		dfs(root, 0);
		return root;
	}

	private int dfs(TreeNode root, int sum) {
		// 变量 sum 表示到到当前节点之前所有父辈节点及其右子树的节点和 (参考图示)。
		if (root == null) {
			return 0;
		}
		// 先递归右边，计算右子树的和
		int right = dfs(root.right, sum);
		// 递归左子树，当前节点的左边子节点必须加上:
		//   1. 当前节点右子树的节点和 sum
		//   2. 当前节点的值 root.val
		//   3. 左节点自己的右子树的节点和 right
		int left = dfs(root.left, sum + right + root.val);
		int val = root.val;
		// 当前节点的值加上传入的 sum 和右子树的和
		root.val += right + sum;
		// 注意这里返回未修改前的节点和
		return right + val + left;
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
