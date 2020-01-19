package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/30/2016.
 * #0106 https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
	// time O(n), space O(n)
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < inorder.length; i++){
			map.put(inorder[i], i);
		}
		return dfs(postorder, 0, postorder.length - 1, 0, postorder.length - 1, map);
	}

	private TreeNode dfs(int[] postorder, int i, int j, int m, int n, Map<Integer, Integer> map){
		if(i > j){
			return null;
		}
		TreeNode root = new TreeNode(postorder[j]);
		if(i == j){
			return root;
		}
		int k = map.get(postorder[j]) - m;
		root.left = dfs(postorder, i, i + k - 1, m, m + k - 1, map);
		root.right = dfs(postorder, i + k, j - 1, m + k + 1, n, map);
		return root;
	}
}
