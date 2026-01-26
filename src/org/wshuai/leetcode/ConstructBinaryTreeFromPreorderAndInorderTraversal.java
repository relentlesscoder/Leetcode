package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/30/2016.
 * #0105 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

	// time O(n), space O(n)
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		// 对前序数组中的每个根节点 - 最左边那个节点，利用中序遍历算出左子树和右子树的大小。
		// 递归左右子树在前序数组中的区间以构造二叉树。
		int n = preorder.length;
		// 节点值到中序遍历索引的哈希表
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(inorder[i], i);
		}
		return dfs(0, n - 1, 0, n - 1,
				preorder, map);
	}

	private TreeNode dfs(int preLeft, int preRight, int inLeft, int inRight,
						 int[] preorder, Map<Integer, Integer> inorder) {
		if (preLeft > preRight || inLeft > inRight) {
			return null;
		}
		// 当前子树的区间的根结点就是前序数组中最左边的节点
		TreeNode root = new TreeNode(preorder[preLeft]);
		// 找到他在中序数组中的位置
		int index = inorder.get(preorder[preLeft]);
		// 计算左子树所在区间
		root.left = dfs(preLeft + 1, preLeft + index - inLeft,
				inLeft, index - 1, preorder, inorder);
		// 计算右子树所在区间
		root.right = dfs(preLeft + index - inLeft + 1, preRight,
				index + 1, inRight, preorder, inorder);
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
