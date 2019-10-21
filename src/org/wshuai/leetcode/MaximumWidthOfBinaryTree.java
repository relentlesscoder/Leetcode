package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/20/2019.
 * #662 https://leetcode.com/problems/maximum-width-of-binary-tree/
 */
public class MaximumWidthOfBinaryTree {
	public int widthOfBinaryTree(TreeNode root) {
		if(root == null){
			return 0;
		}
		int width = 1;
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			while(!queue.isEmpty() && queue.peekFirst() == null){
				queue.pollFirst();
			}
			while(!queue.isEmpty() && queue.peekLast() == null){
				queue.pollLast();
			}
			int size = queue.size();
			width = Math.max(size, width);
			while(size > 0){
				TreeNode curr = queue.pollFirst();
				queue.offerLast(curr == null ? null : curr.left);
				queue.offerLast(curr == null ? null : curr.right);
				size--;
			}
		}
		return width;
	}
}
