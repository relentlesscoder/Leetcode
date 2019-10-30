package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2019.
 * #971 https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/
 */
public class FlipBinaryTreeToMatchPreorderTraversal {
	public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
		List<Integer> path = new ArrayList<>();
		if(dfs(root, voyage, 0, path) == -1){
			List<Integer> result = new ArrayList<>();
			result.add(-1);
			return result;
		}
		return path;
	}

	private int dfs(TreeNode root, int[] voyage, int i, List<Integer> path){
		if(root == null){
			return i;
		}
		if(root.val != voyage[i]){
			return -1;
		}
		int left = dfs(root.left, voyage, i + 1, path);
		if(left != -1){
			return dfs(root.right, voyage, left, path);
		}
		// need a flip
		path.add(root.val);
		int right = dfs(root.right, voyage, i + 1, path);
		if(right != -1){
			return dfs(root.left, voyage, right, path);
		}
		return -1;
	}
}
