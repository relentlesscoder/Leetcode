package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/25/19.
 * #743 https://leetcode.com/problems/network-delay-time/
 */
public class NetworkDelayTime {

	// BFS - Dijkstra MST
	public int networkDelayTime(int[][] times, int N, int K) {
		Map<Integer, List<int[]>> map = new HashMap<>();
		for(int[] t: times){
			map.putIfAbsent(t[0] - 1, new ArrayList<>());
			map.get(t[0] - 1).add(t);
		}
		boolean[] set = new boolean[N];
		int[] dist = new int[N];
		for(int i = 0; i < N; i++){
			dist[i] = Integer.MAX_VALUE;
			set[i] = false;
		}
		dist[K - 1] = 0;
		for(int i = 0; i < N - 1; i++){
			int u = minDist(dist, set);
			set[u] = true;
			if(map.containsKey(u)){
				List<int[]> next = map.get(u);
				for(int[] n: next){
					int v = n[1] - 1;
					if(!set[v] && dist[u] != Integer.MAX_VALUE && dist[u] + n[2] < dist[v]){
						dist[v] = dist[u] + n[2];
					}
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for(int d: dist){
			if(d == Integer.MAX_VALUE){
				return -1;
			}
			max = Math.max(max, d);
		}
		return max;
	}

	private int minDist(int[] dist, boolean[] set){
		int min = Integer.MAX_VALUE;
		int index = -1;

		for(int i = 0; i < dist.length; i++){
			if(!set[i] && dist[i] < min){
				min = dist[i];
				index = i;
			}
		}
		return index;
	}
}
