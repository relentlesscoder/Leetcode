package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 02/26/2017.
 * #0513 https://leetcode.com/problems/find-bottom-left-tree-value/
 */
public class FindBottomLeftTreeValue {
	// time O(n), space O(n)
	public int findBottomLeftValue(TreeNode root) {
		int res = -1;
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			TreeNode cur = queue.pollFirst();
			res = cur.val;
			if(cur.right != null){
				queue.offerLast(cur.right);
			}
			if(cur.left != null){
				queue.offerLast(cur.left);
			}
		}
		return res;
	}
}
