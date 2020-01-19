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
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < inorder.length; i++){
			map.put(inorder[i], i);
		}
		return dfs(preorder, 0, preorder.length - 1, 0, preorder.length - 1, map);
	}

	private TreeNode dfs(int[] preorder, int i, int j, int m, int n, Map<Integer, Integer> map){
		if(i > j){
			return null;
		}
		TreeNode root = new TreeNode(preorder[i]);
		if(i == j){
			return root;
		}
		int k = map.get(preorder[i]) - m;
		root.left = dfs(preorder, i + 1, i + k, m, m + k - 1, map);
		root.right = dfs(preorder, i + k + 1, j, m + k + 1, n, map);
		return root;
	}
}
