package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 10/14/2016.
 * #0101 https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {
	// time O(n)
	public boolean isSymmetricDFS(TreeNode root) {
		if(root == null){
			return true;
		}
		return dfs(root.left, root.right);
	}

	private boolean dfs(TreeNode left, TreeNode right){
		if(left == null && right == null){
			return true;
		}
		if(left == null || right == null){
			return false;
		}
		return left.val == right.val
			&& dfs(left.left, right.right)
			&& dfs(left.right, right.left);
	}

	// time O(n), space O(n)
	public boolean isSymmetricBFS(TreeNode root) {
		if(root == null){
			return true;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		List<Integer> cur = new ArrayList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			cur = new ArrayList<>();
			int size = queue.size();
			while(size-- > 0){
				TreeNode node = queue.pollFirst();
				if(node != null){
					queue.offerLast(node.left);
					queue.offerLast(node.right);
				}
				cur.add(node == null ? null : node.val);
			}
			if(!isLevelSymmetric(cur)){
				return false;
			}
		}
		return true;
	}

	private boolean isLevelSymmetric(List<Integer> cur){
		int left = 0;
		int right = cur.size() - 1;
		while(left <= right){
			Integer i1 = cur.get(left++);
			Integer i2 = cur.get(right--);
			if(i1 == null && i2 == null){
				continue;
			}
			if(i1 == null || i2 == null || i1 != i2){
				return false;
			}
		}
		return true;
	}
}
