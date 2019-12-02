package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/2/19.
 * #871 https://leetcode.com/problems/minimum-number-of-refueling-stops/
 */
public class MinimumNumberOfRefuelingStops {

	// greedy
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		if(startFuel >= target){
			return 0;
		}
		int i = 0, n = stations.length, res = 0, capacity = startFuel;
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		while(capacity < target){
			while(i < n && stations[i][0] <= capacity){
				pq.offer(stations[i++][1]);
			}
			if(pq.isEmpty()){
				return -1;
			}
			capacity += pq.poll();
			res++;
		}
		return res;
	}

	private Map<String, Integer> dp;

	// TLE
	public int minRefuelStopsDFSWithMemorization(int target, int startFuel, int[][] stations) {
		dp = new HashMap<>();
		int res = dfs(0, 0, startFuel, target, stations);
		return res > 500 ? -1 : res;
	}

	private int dfs(int cur, int nextStation, long fuel, int target, int[][] stations){
		long capacity = cur + fuel;
		if(nextStation == stations.length){
			if(capacity < target){
				return 1_000;
			}else{
				return 0;
			}
		}
		if(capacity < stations[nextStation][0]){
			return 1_000;
		}
		String key = cur + "," + fuel;
		if(dp.containsKey(key)){
			return dp.get(key);
		}
		int res = 1_000;
		for(int i = nextStation; i < stations.length; i++){
			if(capacity < stations[i][0]){
				break;
			}
			res = Math.min(res, dfs(stations[i][0], i + 1, capacity - stations[i][0], target, stations));
			res = Math.min(res, 1 + dfs(stations[i][0], i + 1, capacity - stations[i][0] + stations[i][1], target, stations));
		}
		dp.put(key, res);
		return res;
	}
}
