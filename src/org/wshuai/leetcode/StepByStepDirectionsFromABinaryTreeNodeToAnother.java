package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/18/2023.
 * #2096 https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
 */
public class StepByStepDirectionsFromABinaryTreeNodeToAnother {

	// time O(n), space O(n)
	public String getDirections(TreeNode root, int startValue, int destValue) {
		StringBuilder sbStart = new StringBuilder(), sbDest = new StringBuilder();
		findNode(root, startValue, sbStart);
		findNode(root, destValue, sbDest);
		int i = sbStart.length() - 1, j = sbDest.length() - 1;
		while (i >= 0 && j >= 0 && sbStart.charAt(i) == sbDest.charAt(j)) {
			i--;
			j--;
		}
		return "U".repeat(i + 1) + sbDest.reverse().substring(sbDest.length() - 1 - j);
	}

	private boolean findNode(TreeNode node, int val, StringBuilder sb) {
		if (node == null) {
			return false;
		}
		if (node.val == val) {
			return true;
		}
		boolean res = false;
		if (findNode(node.left, val, sb)) {
			res = true;
			sb.append("L");
		} else if (findNode(node.right, val, sb)) {
			res = true;
			sb.append("R");
		}
		return res;
	}

	// time O(n), space O(n)
	public String getDirectionsBFS(TreeNode root, int startValue, int destValue) {
		Map<Integer, int[]> graph = new HashMap<>();
		buildGraph(root, null, graph);
		Set<Integer> visited = new HashSet<>();
		LinkedList<Integer> nodeQueue = new LinkedList<>();
		LinkedList<String> pathQueue = new LinkedList<>();
		visited.add(startValue);
		nodeQueue.offerLast(startValue);
		pathQueue.offerLast("");
		while (!nodeQueue.isEmpty()) {
			int curr = nodeQueue.pollFirst();
			String path = pathQueue.pollFirst();
			if (curr == destValue) {
				return path;
			}
			int[] next = graph.get(curr);
			if (next[0] != -1 && visited.add(next[0])) {
				nodeQueue.offerLast(next[0]);
				pathQueue.offerLast(path + "U");
			}
			if (next[1] != -1 && visited.add(next[1])) {
				nodeQueue.offerLast(next[1]);
				pathQueue.offerLast(path + "L");
			}
			if (next[2] != -1 && visited.add(next[2])) {
				nodeQueue.offerLast(next[2]);
				pathQueue.offerLast(path + "R");
			}
		}
		return "";
	}

	// time O(n), space O(n)
	public String getDirectionsDFS(TreeNode root, int startValue, int destValue) {
		Map<Integer, int[]> graph = new HashMap<>();
		buildGraph(root, null, graph);
		Set<Integer> visited = new HashSet<>();
		return dfs(startValue, destValue, "", visited, graph);
	}

	private String dfs(int curr, int dest, String path, Set<Integer> visited, Map<Integer, int[]> graph) {
		if (curr == dest) {
			return path;
		}
		visited.add(curr);
		int[] next = graph.get(curr);
		if (next[0] != -1 && visited.add(next[0])) {
			String upPath = dfs(next[0], dest, path + "U", visited, graph);
			if (upPath != "") {
				return upPath;
			}
		}
		if (next[1] != -1 && visited.add(next[1])) {
			String leftPath = dfs(next[1], dest, path + "L", visited, graph);
			if (leftPath != "") {
				return leftPath;
			}
		}
		if (next[2] != -1 && visited.add(next[2])) {
			String rightPath = dfs(next[2], dest, path + "R", visited, graph);
			if (rightPath != "") {
				return rightPath;
			}
		}
		return "";
	}

	private void buildGraph(TreeNode node, TreeNode parent, Map<Integer, int[]> graph) {
		if (node == null) {
			return;
		}
		int[] nodes = new int[3];
		nodes[0] = parent != null ? parent.val : -1;
		nodes[1] = node.left != null ? node.left.val : -1;
		nodes[2] = node.right != null ? node.right.val : -1;
		graph.put(node.val, nodes);
		buildGraph(node.left, node, graph);
		buildGraph(node.right, node, graph);
	}
}
