package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 08/08/2019.
 * #0590 https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 */
public class NaryTreePostorderTraversal {
	// time O(n), space O(n)
	public List<Integer> postorder(NaryTreeNode root) {
		LinkedList<Integer> res = new LinkedList<>();
		if(root == null){
			return res;
		}
		LinkedList<NaryTreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			NaryTreeNode cur = queue.pollLast();
			res.offerFirst(cur.val);
			for(NaryTreeNode child : cur.children){
				queue.offerLast(child);
			}
		}
		return res;
	}
}
