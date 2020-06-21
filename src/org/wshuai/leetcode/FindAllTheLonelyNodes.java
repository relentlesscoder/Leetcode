package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 06/06/2020.
 * #1469 https://leetcode.com/problems/find-all-the-lonely-nodes/
 */
public class FindAllTheLonelyNodes {

	// time O(n)
	public List<Integer> getLonelyNodes(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		dfs(root, res);
		return res;
	}

	private void dfs(TreeNode root, List<Integer> res){
		if(root == null){
			return;
		}
		if(root.left != null && root.right == null){
			res.add(root.left.val);
		}
		if(root.left == null && root.right != null){
			res.add(root.right.val);
		}
		dfs(root.left, res);
		dfs(root.right, res);
	}
}
