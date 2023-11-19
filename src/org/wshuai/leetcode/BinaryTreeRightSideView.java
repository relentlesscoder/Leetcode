package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/10/2016.
 * #0199 https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {

	// time O(n)
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		dfs(root, res, 0);
		return res;
	}

	private void dfs(TreeNode node, List<Integer> res, int depth){
		if(node == null){
			return;
		}
		if(depth == res.size()){
			res.add(node.val);
		}
		dfs(node.right, res, depth + 1);
		dfs(node.left, res, depth + 1);
	}
}
