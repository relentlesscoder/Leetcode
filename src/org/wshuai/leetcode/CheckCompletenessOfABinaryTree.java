package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/11/2019.
 * #958 https://leetcode.com/problems/check-completeness-of-a-binary-tree/
 */
public class CheckCompletenessOfABinaryTree {

	public boolean isCompleteTree(TreeNode root) {
		boolean end = false;
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()) {
			TreeNode cur = queue.pollFirst();
			if(cur == null) {
				end = true;
			}
			else{
				if(end){
					return false;
				}
				queue.offerLast(cur.left);
				queue.offerLast(cur.right);
			}
		}
		return true;
	}

	public boolean isCompleteTreeAlternative(TreeNode root) {
		LinkedList<TreeNode> bfs = new LinkedList<>();
		bfs.offerLast(root);
		while(bfs.peekFirst() != null){
			TreeNode node = bfs.pollFirst();
			bfs.offerLast(node.left);
			bfs.offerLast(node.right);
		}
		while(!bfs.isEmpty() && bfs.peekFirst() == null){
			bfs.pollFirst();
		}
		return bfs.isEmpty();
	}


}
