package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/1/2016.
 * #103 https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class BinaryTreeZigzagLevelOrderTraversal {
	// time O(n), space O(n)
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		boolean leftToRight = true;
		LinkedList<TreeNode> queue = new LinkedList<>();
		List<Integer> cur = new ArrayList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			cur = new ArrayList<>();
			int size = queue.size();
			while(size-- > 0){
				TreeNode node = leftToRight ? queue.pollFirst() : queue.pollLast();
				cur.add(node.val);
				if(leftToRight){
					if(node.left != null){
						queue.offerLast(node.left);
					}
					if(node.right != null){
						queue.offerLast(node.right);
					}
				}else{
					if(node.right != null){
						queue.offerFirst(node.right);
					}
					if(node.left != null){
						queue.offerFirst(node.left);
					}
				}
			}
			res.add(cur);
			leftToRight = !leftToRight;
		}
		return res;
	}
}
