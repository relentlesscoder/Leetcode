package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/01/2020.
 * #1602 https://leetcode.com/problems/find-nearest-right-node-in-binary-tree/
 */
public class FindNearestRightNodeInBinaryTree {

	// time O(n), space O(n)
	public TreeNode findNeartestRightNode(TreeNode root, TreeNode u) {
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		boolean seen = false;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				TreeNode cur = queue.pollFirst();
				if(cur == u){
					if(size == 0){
						return null;
					}
					seen = true;
				}else if(seen){
					return cur;
				}
				if(cur.left != null){
					queue.offerLast(cur.left);
				}
				if(cur.right != null){
					queue.offerLast(cur.right);
				}
			}
		}
		return null;
	}
}
