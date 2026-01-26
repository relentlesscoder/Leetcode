package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/12/2019.
 * #0889 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

	// time O(n), space O(n)
	public TreeNode constructFromPrePostArray(int[] preorder, int[] postorder) {
		// 优化: 因为节点值在 [1, 30] 之内所以可用数组代替哈希表
		int n = postorder.length;
		int[] preIndexMap = new int[31];
		for (int i = 0; i < n; i++) {
			preIndexMap[preorder[i]] = i;
		}
		return dfs(0, n - 1, 0, n - 1,
				preorder, postorder, preIndexMap);
	}

	private TreeNode dfs(int preLeft, int preRight, int postLeft, int postRight,
						 int[] preorder, int[] postorder,
						 int[] preIndexMap) {
		if (preLeft > preRight || postLeft > postRight) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preLeft]);
		if (preLeft == preRight) {
			return root;
		}
		int leftInPre = preLeft + 1, rightInPost = postRight - 1,
				rightInPre = preIndexMap[postorder[rightInPost]];
		root.left = dfs(leftInPre, rightInPre - 1,
				postLeft, postLeft + rightInPre - leftInPre - 1,
				preorder, postorder, preIndexMap);
		root.right = dfs(rightInPre, preRight,
				postLeft + rightInPre - leftInPre, rightInPost,
				preorder, postorder, preIndexMap);
		return root;
	}

	// time O(n), space O(n)
	public TreeNode constructFromPrePostHashMap(int[] preorder, int[] postorder) {
		// #0105 相似的思路
		// 对前序数组中的每个根节点 - 最左边那个节点，即使后续遍历中左右边的那个节点。并且前序数组
		// 根结点右边的那个节点是左子树的根结点而后续数组中根结点左边的那个节点是右子树的根结点。所
		// 以只要找到右子树根结点在前序遍历中的位置即可计算左右子树的大小然后递归左右子树在前序数组
		// 中的区间以构造二叉树。
		int n = postorder.length;
		// 节点值到前序遍历索引的哈希表
		Map<Integer, Integer> preIndexMap = new HashMap<>();
		for (int i = 0; i < n; i++) {
			preIndexMap.put(preorder[i], i);
		}
		return dfs1(0, n - 1, 0, n - 1,
				preorder, postorder, preIndexMap);
	}

	private TreeNode dfs1(int preLeft, int preRight, int postLeft, int postRight,
						 int[] preorder, int[] postorder,
						 Map<Integer, Integer> preIndexMap) {
		if (preLeft > preRight || postLeft > postRight) {
			return null;
		}
		// 当前子树的区间的根结点就是前序数组中最左边的节点
		TreeNode root = new TreeNode(preorder[preLeft]);
		if (preLeft == preRight) {
			return root;
		}

		int leftInPre = preLeft + 1, // 左子树在前序数组中的位置
				rightInPost = postRight - 1, // 右子树在后序数组中的位置
				// 利用哈希表找到右子树根结点在前序数组中的位置
				rightInPre = preIndexMap.get(postorder[rightInPost]);
		// 计算左子树所在区间
		root.left = dfs1(leftInPre, rightInPre - 1,
				postLeft, postLeft + rightInPre - leftInPre - 1,
				preorder, postorder, preIndexMap);
		// 计算右子树所在区间
		root.right = dfs1(rightInPre, preRight,
				postLeft + rightInPre - leftInPre, rightInPost,
				preorder, postorder, preIndexMap);
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
