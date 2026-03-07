package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 10/29/2019.
 * #1145 https://leetcode.com/problems/binary-tree-coloring-game/
 */
public class BinaryTreeColoringGame {

	private int subTreeSize = -1;
	private int leftSize = -1;
	private int rightSize = -1;

	// time O(n), space O(h)
	public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
		subTreeSize = -1; // 代表以 x 为根结点的子树的节点数
		leftSize = -1; // 代表 x 的左子树的节点数
		rightSize = -1; // 代表 x 的右子树的节点数
		dfs(root, x);
		return leftSize > n - leftSize // 如左子树的节点数大于剩余的节点数选择左子节点则必胜
				|| rightSize > n - rightSize // 如右子树的节点数大于剩余的节点数选择右子节点则必胜
				|| n - subTreeSize > subTreeSize; // 如剩余节点数大于以 x 为根结点的子树的节点数选择父节点则必胜
	}

	private int dfs(TreeNode root, int x) {
		if (root == null || subTreeSize != -1) { // 优化 subTreeSize != -1 以提早结束搜索
			return 0;
		}
		int left = dfs(root.left, x), right = dfs(root.right, x);
		int sum = left + right + 1;
		if (root.val == x) {
			subTreeSize = sum;
			leftSize = left;
			rightSize = right;
		}
		return sum;
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
