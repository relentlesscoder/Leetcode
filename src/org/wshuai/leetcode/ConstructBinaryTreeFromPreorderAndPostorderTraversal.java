package org.wshuai.leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/12/2019.
 * #0889 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

	// time O(n), space O(n)
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		Map<Integer, Integer> map = new HashMap<>();
		int n = post.length;
		for(int i = 0; i < n; i++){
			map.put(post[i], i);
		}
		return dfs(pre, 0, n - 1, 0, n - 1, map);
	}

	private TreeNode dfs(int[] preorder, int i, int j, int m, int n, Map<Integer, Integer> map){
		// i, j denotes range in pre order
		// m, n denotes range in post order
		if(i > j){
			return null;
		}
		TreeNode root = new TreeNode(preorder[i]);
		if(i == j){
			return root;
		}
		// use index in post order to calculate the number of nodes in left tree
		int k = map.get(preorder[i + 1]) - m;
		root.left = dfs(preorder, i + 1, i + k + 1, m, m + k, map);
		root.right = dfs(preorder, i + k + 2, j, m + k + 1, n, map);
		return root;
	}
}
