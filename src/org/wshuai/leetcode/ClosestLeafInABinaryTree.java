package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/15/2019.
 * #0742 https://leetcode.com/problems/closest-leaf-in-a-binary-tree/
 */
public class ClosestLeafInABinaryTree {

	// time O(n), space O(n)
	public int findClosestLeaf(TreeNode root, int k) {
		Map<TreeNode, Integer> map = new HashMap<>();
		int[] min = new int[]{Integer.MAX_VALUE, 0};
		// find the distance of nodes from node k (back to the current)
		// along the path from root to node k
		findNode(root, k, map);
		// calculate distance and find closest leave node for all nodes
		findLeaves(root, 0, map, min);
		return min[1];
	}

	private void findNode(TreeNode root, int k, Map<TreeNode, Integer> map){
		if(root == null){
			return;
		}
		if(root.val == k){
			map.put(root, 0);
			return;
		}
		findNode(root.left, k, map);
		findNode(root.right, k, map);
		if(map.containsKey(root.left)){
			map.put(root, map.get(root.left) + 1);
		}else if(map.containsKey(root.right)){
			map.put(root, map.get(root.right) + 1);
		}
	}

	private void findLeaves(TreeNode root, int dist, Map<TreeNode, Integer> map, int[] min){
		if(root == null){
			return;
		}
		int cur = map.containsKey(root) ? map.get(root) : dist + 1;
		if(root.left == null && root.right == null && cur < min[0]){
			min[0] = cur;
			min[1] = root.val;
		}
		findLeaves(root.left, cur, map, min);
		findLeaves(root.right, cur, map, min);
	}

	// time O(n), space O(n)
	// DFS to build the graph, BFS to search for the closest leave node
	public int findClosestLeafGraph(TreeNode root, int k) {
		Map<Integer, Set<Integer>> adj = new HashMap<>();
		Set<Integer> leaves = new HashSet<>();
		adj.put(root.val, new HashSet<>());
		dfs(root, adj, leaves);
		LinkedList<Integer> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		queue.offerLast(k);
		visited.add(k);
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			if(leaves.contains(cur)){
				return cur;
			}
			for(int next : adj.get(cur)){
				if(!visited.contains(next)){
					visited.add(next);
					queue.offerLast(next);
				}
			}
		}
		return -1;
	}

	private void dfs(TreeNode root, Map<Integer, Set<Integer>> adj, Set<Integer> leaves){
		if(root == null){
			return;
		}
		if(root.left == null && root.right == null){
			leaves.add(root.val);
			return;
		}
		if(root.left != null){
			adj.put(root.left.val, new HashSet<>());
			adj.get(root.val).add(root.left.val);
			adj.get(root.left.val).add(root.val);
		}
		if(root.right != null){
			adj.put(root.right.val, new HashSet<>());
			adj.get(root.val).add(root.right.val);
			adj.get(root.right.val).add(root.val);
		}
		dfs(root.left, adj, leaves);
		dfs(root.right, adj, leaves);
	}
}
