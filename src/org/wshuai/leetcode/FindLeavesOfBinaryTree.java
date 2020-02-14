package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/01/2016.
 * #0366 https://leetcode.com/problems/find-leaves-of-binary-tree/
 */
public class FindLeavesOfBinaryTree {
	// time O(n)
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(root, res);
		return res;
	}

	private int dfs(TreeNode root, List<List<Integer>> res){
		if(root == null){
			return -1;
		}
		int left = dfs(root.left, res), right = dfs(root.right, res);
		int depth = Math.max(left, right) + 1;
		if(depth >= res.size()){
			res.add(new ArrayList<>());
		}
		res.get(depth).add(root.val);
		return depth;
	}
}
