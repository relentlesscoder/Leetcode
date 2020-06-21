package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/25/2019.
 * #0743 https://leetcode.com/problems/network-delay-time/
 */
public class NetworkDelayTime {

	// time O(V*log(V)), space O(V)
	// Dijkstra using priority queue
	public int networkDelayTime(int[][] times, int N, int K) {
		int res = 0;
		boolean[] visited = new boolean[N + 1];
		ArrayList<int[]>[] adj = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++){
			adj[i] = new ArrayList<>();
		}
		for(int[] t : times){
			adj[t[0]].add(t);
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.offer(new int[]{K, 0});
		while(!pq.isEmpty()){
			int[] cur = pq.poll();
			int u = cur[0], d = cur[1];
			if(visited[u]){
				continue;
			}
			visited[u] = true;
			res = d;
			N--;
			for(int[] edge : adj[u]){
				pq.offer(new int[]{edge[1], d + edge[2]});
			}
		}
		return N == 0 ? res : -1;
	}


	// time O(V*E), space O(V)
	// Bellman-Ford
	public int networkDelayTimeBellmanFord(int[][] times, int N, int K) {
		int res = 0;
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		for(int i = 0; i < N; i++){
			for(int[] edge : times){
				int u = edge[0], v = edge[1], d = edge[2];
				if(dist[u] != Integer.MAX_VALUE && dist[u] + d < dist[v]){
					dist[v] = dist[u] + d;
				}
			}
		}
		for(int i = 1; i <= N; i++){
			if(dist[i] == Integer.MAX_VALUE){
				return -1;
			}
			res = Math.max(res, dist[i]);
		}
		return res;
	}

	// time O(V^2), space O(V)
	// Dijkstra verbose
	public int networkDelayTimeVerbose(int[][] times, int N, int K) {
		int res = 0;
		int[] dist = new int[N + 1];
		boolean[] set = new boolean[N + 1];
		ArrayList<int[]>[] adj = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++){
			dist[i] = Integer.MAX_VALUE;
			adj[i] = new ArrayList<>();
		}
		for(int[] t : times){
			adj[t[0]].add(t);
		}
		dist[K] = 0;
		for(int i = 1; i < N; i++){
			int u = minDistance(dist, set);
			set[u] = true;
			for(int[] edge : adj[u]){
				int v = edge[1], d = edge[2];
				if(!set[v] && dist[u] != Integer.MAX_VALUE && dist[u] + d < dist[v]){
					dist[v] = dist[u] + d;
				}
			}
		}
		for(int i = 1; i <= N; i++){
			if(dist[i] == Integer.MAX_VALUE){
				return -1;
			}
			res = Math.max(res, dist[i]);
		}
		return res;
	}

	private int minDistance(int[] dist, boolean[] set){
		int res = -1, min = Integer.MAX_VALUE;
		for(int i = 1; i < dist.length; i++){
			if(!set[i] && dist[i] < min){
				min = dist[i];
				res = i;
			}
		}
		return res;
	}
}
