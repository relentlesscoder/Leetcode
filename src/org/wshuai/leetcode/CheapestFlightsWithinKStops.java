package org.wshuai.leetcode;


import java.util.*;

/**
 * Created by Wei on 09/22/2019.
 * #0787 https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */
public class CheapestFlightsWithinKStops {

	// time O(n^k*log(n^k)), space O(n^k)
	//https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/361711/Java-DFSBFSBellman-Ford-Dijkstra's
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		Map<Integer, List<int[]>> adj = new HashMap<>();
		for(int[] f : flights){
			adj.putIfAbsent(f[0], new ArrayList<>());
			adj.get(f[0]).add(new int[]{f[1], f[2]});
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		pq.offer(new int[]{0, src, K + 1});
		while(!pq.isEmpty()){
			int[] cur = pq.poll();
			int cost = cur[0], city = cur[1], stops = cur[2];
			if(city == dst){
				return cost;
			}
			if(stops > 0){
				if(!adj.containsKey(city)){
					continue;
				}
				for(int[] next : adj.get(city)){
					pq.offer(new int[]{cost + next[1], next[0], stops - 1});
				}
			}
		}
		return -1;
	}

	// time O(n*k), space O(n*k)
	public int findCheapestPriceBellmanFord(int n, int[][] flights, int src, int dst, int K) {
		int[] cost = new int[n];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[src] = 0;
		for(int i = 0; i <= K; i++){
			// use new array to move one step each time
			int[] temp = Arrays.copyOf(cost, n);
			for(int[] f : flights){
				int cur = f[0], next = f[1], price = f[2];
				if(cost[cur] == Integer.MAX_VALUE){
					continue;
				}
				temp[next] = Math.min(temp[next], cost[cur] + price);
			}
			cost = temp;
		}
		return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
	}
}


