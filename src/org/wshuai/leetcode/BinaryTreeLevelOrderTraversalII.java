package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 09/19/2016.
 * #0107 https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */
public class BinaryTreeLevelOrderTraversalII {
	// time O(n), space O(n)
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		LinkedList<List<Integer>> res = new LinkedList<>();
		if(root == null){
			return res;
		}
		List<Integer> cur = new ArrayList<>();
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			cur = new ArrayList<>();
			int size = queue.size();
			while(size-- > 0){
				TreeNode node = queue.pollFirst();
				cur.add(node.val);
				if(node.left != null){
					queue.offerLast(node.left);
				}
				if(node.right != null){
					queue.offerLast(node.right);
				}
			}
			res.offerFirst(cur);
		}
		return res;
	}
}
