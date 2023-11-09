package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 11/08/2023.
 * #2050 https://leetcode.com/problems/parallel-courses-iii/
 */
public class ParallelCoursesIII {

	// time O(V + E), space O(V + E)
	public int minimumTime(int n, int[][] relations, int[] time) {
		int res = 0;
		int[] indegree = new int[n], starts = new int[n];
		List<Integer>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] r : relations) {
			graph[r[0] - 1].add(r[1] - 1);
			indegree[r[1] - 1]++;
		}
		Deque<int[]> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (indegree[i] == 0) {
				visited[i] = true;
				queue.offer(new int[]{i, time[i]});
			}
		}
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] curr = queue.poll();
				res = Math.max(res, curr[1]);
				for (int next : graph[curr[0]]) {
					if (visited[next]) {
						continue;
					}
					starts[next] = Math.max(starts[next], time[next] + curr[1]);
					if (--indegree[next] == 0) {
						visited[next] = true;
						queue.offer(new int[]{next, starts[next]});
					}
				}
			}
		}
		return res;
	}

	// time O(V + E), space O(V + E)
	public int minimumTimeTopDownDP(int n, int[][] relations, int[] time) {
		int res = 0;
		Integer[] memo = new Integer[n];
		List<Integer>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] r : relations) {
			graph[r[0] - 1].add(r[1] - 1);
		}
		for (int i = 0; i < n; i++) {
			res = Math.max(res, dfs(i, time, graph, memo));
		}
		return res;
	}

	private int dfs(int curr, int[] time, List<Integer>[] graph, Integer[] memo) {
		if (memo[curr] != null) {
			return memo[curr];
		}
		if (graph[curr].size() == 0) {
			return time[curr];
		}
		int res = 0;
		for (int next : graph[curr]) {
			res = Math.max(res, dfs(next, time, graph, memo));
		}
		memo[curr] = res + time[curr];
		return memo[curr];
	}
}
