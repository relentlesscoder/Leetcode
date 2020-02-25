package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 08/09/2019.
 * #0429 https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 */
public class NaryTreeLevelOrderTraversal {
	// time O(n), space O(n)
	public List<List<Integer>> levelOrder(NaryTreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		LinkedList<NaryTreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			List<Integer> levelNodes = new ArrayList<>();
			while(size-- > 0){
				NaryTreeNode cur = queue.pollFirst();
				levelNodes.add(cur.val);
				for(NaryTreeNode child : cur.children){
					queue.offerLast(child);
				}
			}
			res.add(levelNodes);
		}
		return res;
	}
}
