package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 9/25/19.
 * #865 https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 */
public class SmallestSubtreeWithAllTheDeepestNodes {
	private TreeNode res;
	private int maxDepth;
	private Map<Integer, Set<TreeNode>> map;

	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		res = root;
		maxDepth = 0;
		map = new HashMap<>();
		dfsDepth(root, 0);
		if(map.get(maxDepth).size() == 1){
			return map.get(maxDepth).iterator().next();
		}
		dfs(root);
		return res;
	}

	private void dfsDepth(TreeNode node, int depth){
		if(node == null){
			return;
		}
		if(!map.containsKey(depth)){
			map.put(depth, new HashSet<TreeNode>());
		}
		map.get(depth).add(node);
		maxDepth = Math.max(maxDepth, depth);
		dfsDepth(node.left, depth + 1);
		dfsDepth(node.right, depth + 1);
	}

	private int dfs(TreeNode node){
		if(node == null){
			return 0;
		}
		if(map.get(maxDepth).contains(node)){
			return 1;
		}
		int left = dfs(node.left);
		int right = dfs(node.right);
		if(left == 1 && right == 1){
			res = node;
			return 1;
		}else if(left == 1 || right == 1){
			return 1;
		}
		return 0;
	}
}
