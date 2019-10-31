package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/30/19.
 * #623 https://leetcode.com/problems/add-one-row-to-tree/
 */
public class AddOneRowToTree {

	// DFS (beats 100%)
	public TreeNode addOneRow(TreeNode root, int v, int d) {
		if(d == 1){
			TreeNode newRoot = new TreeNode(v);
			newRoot.left = root;
			return newRoot;
		}
		dfs(root, 1, v, d);
		return root;
	}

	private void dfs(TreeNode root, int depth, int v, int d){
		if(root == null){
			return;
		}
		if(depth == d - 1){
			TreeNode left = root.left;
			TreeNode right = root.right;
			root.left = new TreeNode(v);
			root.right = new TreeNode(v);
			root.left.left = left;
			root.right.right = right;
			return;
		}
		dfs(root.left, depth + 1, v, d);
		dfs(root.right, depth + 1, v, d);
	}

	// BFS
	public TreeNode addOneRowBFS(TreeNode root, int v, int d) {
		if(d == 1){
			TreeNode newRoot = new TreeNode(v);
			newRoot.left = root;
			return newRoot;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		int depth = 1;
		queue.offerLast(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				TreeNode node = queue.pollFirst();
				TreeNode left = node.left;
				TreeNode right = node.right;
				if(depth == d - 1){
					node.left = new TreeNode(v);
					node.right = new TreeNode(v);
					node.left.left = left;
					node.right.right = right;
				}else{
					if(left != null){
						queue.offerLast(left);
					}
					if(right != null){
						queue.offerLast(right);
					}
				}
			}
			depth++;
		}
		return root;
	}
}
