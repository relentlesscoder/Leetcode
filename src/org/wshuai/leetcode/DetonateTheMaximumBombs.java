package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by Wei on 09/26/2023.
 * #2101 https://leetcode.com/problems/detonate-the-maximum-bombs/
 */
public class DetonateTheMaximumBombs {

	// time O(n^3), space O(n^2)
	public int maximumDetonation(int[][] bombs) {
		int n = bombs.length;
		ArrayList<Integer>[] graph = new ArrayList[n]; // graph has n nodes and n^2 edges in worst case

		// build directed graph
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				int ix = bombs[i][0], iy = bombs[i][1], ir = bombs[i][2];
				int jx = bombs[j][0], jy = bombs[j][1];
				if (1L * ir * ir >= 1L * (ix - jx) * (ix - jx) + 1L * (iy - jy) * (iy - jy)) {
					graph[i].add(j);
				}
			}
		}

		// dfs to find the max result
		int res = 0;
		for (int i = 0; i < n; i++) {
			int count = dfsRecursive(i, new boolean[n], graph); // each dfs takes n^2 in worst case
			// int count = dfsIterative(i, graph);
			// int count = bfs(i, graph);
			res = Math.max(res, count);
		}
		return res;
	}

	// time O(n^2), space O(n)
	private int dfsRecursive(int curr, boolean[] visited, ArrayList<Integer>[] graph) {
		visited[curr] = true;
		int count = 1;
		for (int next : graph[curr]) {
			if (!visited[next]) {
				count += dfsRecursive(next, visited, graph);
			}
		}
		return count;
	}

	// time O(n^2), space O(n)
	private int dfsIterative(int curr, ArrayList<Integer>[] graph) {
		int res = 0, n = graph.length;
		boolean[] visited = new boolean[n];
		Stack<Integer> stack = new Stack();
		stack.push(curr);
		visited[curr] = true;
		while (!stack.isEmpty()) {
			int node = stack.pop();
			res++;
			for (int next : graph[node]) {
				if (!visited[next]) {
					visited[next] = true;
					stack.push(next);
				}
			}
		}
		return res;
	}

	// time O(n^2), space O(n)
	private int bfs(int curr, ArrayList<Integer>[] graph) {
		int res = 0, n = graph.length;
		boolean[] visited = new boolean[n];
		Deque<Integer> queue = new ArrayDeque();
		queue.offerLast(curr);
		visited[curr] = true;
		while (!queue.isEmpty()) {
			int node = queue.pollFirst();
			res++;
			for (int next : graph[node]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.offerLast(next);
				}
			}
		}
		return res;
	}
}
