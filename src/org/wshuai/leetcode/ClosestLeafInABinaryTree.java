package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/15/2019.
 * #0742 https://leetcode.com/problems/closest-leaf-in-a-binary-tree/
 */
public class ClosestLeafInABinaryTree {

	private int min;
	private TreeNode minNode;

	// time O(n), space O(n)
	// same as #0863
	public int findClosestLeaf(TreeNode root, int k) {
		min = Integer.MAX_VALUE;
		minNode = null;
		Map<TreeNode, Integer> map = new HashMap<>();
		findNode(root, k, map);
		findClosest(root, 0, map);
		return minNode.val;
	}

	private int findNode(TreeNode root, int k, Map<TreeNode, Integer> map){
		if(root == null){
			return -1;
		}
		if(root.val == k){
			map.put(root, 0);
			return 0;
		}
		int left = findNode(root.left, k, map);
		if(left >= 0){
			map.put(root, left + 1);
			return left + 1;
		}
		int right = findNode(root.right, k, map);
		if(right >= 0){
			map.put(root, right + 1);
			return right + 1;
		}
		return -1;
	}

	private void findClosest(TreeNode root, int dist, Map<TreeNode, Integer> map){
		dist = map.getOrDefault(root, dist);
		if(root.left == null && root.right == null && dist < min){
			min = dist;
			minNode = root;
		}
		if(root.left != null){
			findClosest(root.left, dist + 1, map);
		}
		if(root.right != null){
			findClosest(root.right, dist + 1, map);
		}
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
