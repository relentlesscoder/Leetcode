package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 9/23/19.
 * #987 https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 */
public class VerticalOrderTraversalOfABinaryTree {
	private TreeMap<Integer, PriorityQueue<TreeNodeInfo>> map;

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		map = new TreeMap<>();
		dfs(root, 0, 0);
		for(Map.Entry<Integer, PriorityQueue<TreeNodeInfo>> entry: map.entrySet()){
			PriorityQueue<TreeNodeInfo> queue = entry.getValue();
			List<Integer> lst = new ArrayList<>();
			while(!queue.isEmpty()){
				lst.add(queue.poll().val);
			}
			res.add(lst);
		}
		return res;
	}

	private void dfs(TreeNode node, int x, int y){
		if(node == null){
			return;
		}
		if(!map.containsKey(x)){
			map.put(x, new PriorityQueue<TreeNodeInfo>((a, b) -> a.y == b.y ? a.val - b.val : a.y - b.y));
		}
		map.get(x).add(new TreeNodeInfo(node.val, y));
		dfs(node.left, x-1, y+1);
		dfs(node.right, x+1, y+1);
	}
}

class TreeNodeInfo{
	public int val;
	public int y;

	public TreeNodeInfo(int val, int y){
		this.val = val;
		this.y = y;
	}
}
