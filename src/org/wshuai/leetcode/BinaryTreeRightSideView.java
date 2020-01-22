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
		List<TreeNode> cur = new ArrayList<>(),
			next = new ArrayList<>();
		cur.add(root);
		while(cur.size() > 0){
			res.add(cur.get(cur.size() - 1).val);
			for(TreeNode node : cur){
				if(node.left != null){
					next.add(node.left);
				}
				if(node.right != null){
					next.add(node.right);
				}
			}
			cur = next;
			next = new ArrayList<>();
		}
		return res;
	}

	// time O(n)
	public List<Integer> rightSideViewRecursive(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		dfs(root, res, 0);
		return res;
	}

	private void dfs(TreeNode root, List<Integer> res, int depth){
		if(root == null){
			return;
		}
		if(depth == res.size()){
			res.add(root.val);
		}
		dfs(root.right, res, depth + 1);
		dfs(root.left, res, depth + 1);
	}
}
