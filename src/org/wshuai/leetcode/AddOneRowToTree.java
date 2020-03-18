package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/30/2019.
 * #0623 https://leetcode.com/problems/add-one-row-to-tree/
 */
public class AddOneRowToTree {

	// time O(n)
	public TreeNode addOneRow(TreeNode root, int v, int d) {
		if(d == 1){
			TreeNode res = new TreeNode(v);
			res.left = root;
			return res;
		}
		dfs(root, 1, v, d - 1);
		return root;
	}

	private void dfs(TreeNode root, int cur, int v, int d){
		if(root == null){
			return;
		}
		if(cur == d){
			TreeNode left = root.left, right = root.right;
			root.left = new TreeNode(v);
			root.right = new TreeNode(v);
			root.left.left = left;
			root.right.right = right;
			return;
		}
		dfs(root.left, cur + 1, v, d);
		dfs(root.right, cur + 1, v, d);
	}

	// time O(n), space O(n)
	public TreeNode addOneRowBFS(TreeNode root, int v, int d) {
		if(d == 1){
			TreeNode res = new TreeNode(v);
			res.left = root;
			return res;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		int depth = 1;
		while(depth < d - 1){
			int size = queue.size();
			while(size-- > 0){
				TreeNode cur = queue.pollFirst();
				if(cur.left != null){
					queue.offerLast(cur.left);
				}
				if(cur.right != null){
					queue.offerLast(cur.right);
				}
			}
			depth++;
		}
		for(TreeNode node : queue){
			TreeNode left = node.left, right = node.right;
			node.left = new TreeNode(v);
			node.left.left = left;
			node.right = new TreeNode(v);
			node.right.right = right;
		}
		return root;
	}
}
