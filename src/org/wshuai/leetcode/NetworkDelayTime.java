package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/25/2019.
 * #0743 https://leetcode.com/problems/network-delay-time/
 */
public class NetworkDelayTime {

	// time O(V*log(V)), space O(V)
	public int networkDelayTimeDijkstraOptimized(int[][] times, int n, int k) {
		int res = 0;
		List<int[]>[] adj = buildGraph(times, n);
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		boolean[] visited = new boolean[n];
		pq.offer(new int[]{0, k - 1});
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (visited[cur[1]]) {
				continue;
			}
			visited[cur[1]] = true;
			res = cur[0];
			if (--n == 0) {
				return res;
			}
			for (int[] next : adj[cur[1]]) {
				pq.offer(new int[]{cur[0] + next[1], next[0]});
			}
		}
		return -1;
	}

	// DFS: time O((V - 1)! + E * log(E)), space O(V + E)
	// BFS: time O(V * E), space O(V * E)
	// Dijkstra: time O(V + E * log(V)), space O(V + E)
	public int networkDelayTimeDFSBFS(int[][] times, int n, int k) {
		int res = 0;
		List<int[]>[] graph = buildGraph(times, n);
		int[] timeReceivedSignal = new int[n];
		Arrays.fill(timeReceivedSignal, Integer.MAX_VALUE);
		dijkstra(k - 1, 0, graph, timeReceivedSignal);
		// dfs(k - 1, 0, graph, timeReceivedSignal);
		// bfs(k - 1, 0, graph, timeReceivedSignal);
		for (int time : timeReceivedSignal) {
			res = Math.max(res, time);
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private void dijkstra(int source, int time, List<int[]>[] graph, int[] timeReceivedSignal) {
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		queue.offer(new int[]{source, 0});
		timeReceivedSignal[source] = time;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			for (int[] next : graph[curr[0]]) {
				if (timeReceivedSignal[next[0]] > curr[1] + next[1]) {
					timeReceivedSignal[next[0]] = curr[1] + next[1];
					queue.offer(new int[]{next[0], timeReceivedSignal[next[0]]});
				}
			}
		}
	}

	private void bfs(int source, int time, List<int[]>[] graph, int[] timeReceivedSignal) {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.offer(source);
		timeReceivedSignal[source] = time;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (int[] next : graph[curr]) {
				if (timeReceivedSignal[next[0]] > timeReceivedSignal[curr] + next[1]) {
					timeReceivedSignal[next[0]] = timeReceivedSignal[curr] + next[1];
					queue.offer(next[0]);
				}
			}
		}
	}

	private void dfs(int curr, int time, List<int[]>[] graph, int[] timeReceivedSignal) {
		if (timeReceivedSignal[curr] <= time) {
			return;
		}
		timeReceivedSignal[curr] = time;
		for (int[] next : graph[curr]) {
			dfs(next[0], time + next[1], graph, timeReceivedSignal);
		}
	}

	private List<int[]>[] buildGraph(int[][] times, int n) {
		List<int[]>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] t : times) {
			graph[t[0] - 1].add(new int[]{t[1] - 1, t[2]});
		}
		/**
		 * sorting only benefits DFS
		 * for (int i = 0; i < n; i++) {
		 *   Collections.sort(graph[i], (a, b) -> a[1] - b[1]);
		 *   }
		 */
		return graph;
	}

	// time O(V*E), space O(V)
	public int networkDelayTimeBellmanFord(int[][] times, int N, int K) {
		int res = 0;
		int[] cost = new int[N + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[K] = 0;
		for (int i = 0; i < N; i++) {
			for (int[] t : times) {
				int u = t[0], v = t[1], w = t[2];
				if (cost[u] != Integer.MAX_VALUE && cost[u] + w < cost[v]) {
					cost[v] = cost[u] + w;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (cost[i] == Integer.MAX_VALUE) {
				return -1;
			}
			res = Math.max(res, cost[i]);
		}
		return res;
	}
}
