package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 07/12/2020.
 * #1506 https://leetcode.com/problems/find-root-of-n-ary-tree/
 */
public class FindRootOfNaryTree {

	// time O(n), space O(1)
	public NaryTreeNode findRoot(List<NaryTreeNode> tree) {
		long sum = 0;
		for(NaryTreeNode node : tree){
			// add "in" edge
			sum += node.val;
			for(NaryTreeNode child : node.children){
				// minus "out" edge
				sum -= child.val;
			}
		}
		// each node will have exact one edge flow in to
		// and out from it except the root node
		for(NaryTreeNode node : tree){
			if(node.val == sum){
				return node;
			}
		}
		return null;
	}
}
