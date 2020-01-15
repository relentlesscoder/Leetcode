package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 01/26/2016.
 * #0102 https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal {
	// time O(n), space O(n)
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		List<Integer> cur = new ArrayList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			cur = new ArrayList<>();
			int size = queue.size();
			while(size-- > 0){
				TreeNode node = queue.pollFirst();
				if(node.left != null){
					queue.offerLast(node.left);
				}
				if(node.right != null){
					queue.offerLast(node.right);
				}
				cur.add(node.val);
			}
			res.add(cur);
		}
		return res;
	}
}
