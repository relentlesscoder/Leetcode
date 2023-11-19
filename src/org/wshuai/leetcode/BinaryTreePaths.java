package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/26/2016.
 * #0257 https://leetcode.com/problems/binary-tree-paths/
 */
public class BinaryTreePaths {

	// time O(n)
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		dfs(root, "", res);
		return res;
	}

	private void dfs(TreeNode root, String cur, List<String> res){
		cur += Integer.toString(root.val);
		if(root.left == null && root.right == null){
			res.add(cur);
			return;
		}
		cur += "->";
		if(root.left != null){
			dfs(root.left, cur, res);
		}
		if(root.right != null){
			dfs(root.right, cur, res);
		}
	}
}
