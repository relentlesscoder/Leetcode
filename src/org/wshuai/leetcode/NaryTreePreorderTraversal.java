package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 08/08/2019.
 * #0589 https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 */
public class NaryTreePreorderTraversal {
	// time O(n), space O(n)
	public List<Integer> preorderIterative(NaryTreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		LinkedList<NaryTreeNode> queue = new LinkedList<>();
		queue.offerFirst(root);
		while(!queue.isEmpty()){
			NaryTreeNode cur = queue.pollFirst();
			res.add(cur.val);
			for(int i = cur.children.size() - 1; i >= 0; i--){
				queue.offerFirst(cur.children.get(i));
			}
		}
		return res;
	}

	// time O(n)
	public List<Integer> preorderRecursive(NaryTreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		dfs(root, res);
		return res;
	}

	private void dfs(NaryTreeNode root, List<Integer> res){
		res.add(root.val);
		if(root.children != null && root.children.size() > 0){
			for(NaryTreeNode child : root.children){
				dfs(child, res);
			}
		}
	}
}
