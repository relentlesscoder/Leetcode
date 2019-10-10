package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 2/26/17.
 * #513 https://leetcode.com/problems/find-bottom-left-tree-value/
 */
public class FindBottomLeftTreeValue {
	//O(n)
	public int findBottomLeftValue(TreeNode root) {
		TreeNode first = root;
		Queue<TreeNode> curr = new LinkedList<TreeNode>();
		Queue<TreeNode> nxt = new LinkedList<TreeNode>();
		curr.offer(root);
		while (!curr.isEmpty()) {
			TreeNode node = curr.poll();
			if (node.left != null) {
				nxt.offer(node.left);
			}
			if (node.right != null) {
				nxt.offer(node.right);
			}
			if (curr.isEmpty()) {
				if (!nxt.isEmpty()) {
					first = nxt.peek();
				}
				curr = nxt;
				nxt = new LinkedList<TreeNode>();
			}
		}
		return first.val;
	}
}
