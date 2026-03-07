package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 05/17/2020.
 * #1448 https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 */
public class CountGoodNodesInBinaryTree {

	// time O(n), space O(h)
	public int goodNodes(TreeNode root) {
		return dfs(root, Integer.MIN_VALUE);
	}

	private int dfs(TreeNode root, int max) {
		if (root == null) {
			return 0;
		}
		int res = 0;
		if (max <= root.val) {
			res++;
		}
		max = Math.max(max, root.val);
		res += dfs(root.left, max);
		res += dfs(root.right, max);
		return res;
	}

    private int res;

    // time O(n), space O(h)
    public int goodNodesNoReturn(TreeNode root) {
        res = 0;
		dfsNoReturn(root, Integer.MIN_VALUE);
        return res;
    }

    private void dfsNoReturn(TreeNode root, int max) {
		// max 记录的是从根结点出发到达该节点前所有节点值的最大值
        if (root == null) {
            return;
        }
		// 如果最大值小于等于当前节点，则当前节点合法
        if (max <= root.val) {
            res++;
        }
		// 更新最大值
        max = Math.max(max, root.val);
		// 递归左右子树
		dfsNoReturn(root.left, max);
		dfsNoReturn(root.right, max);
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
