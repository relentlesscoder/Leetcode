package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 05/31/2020.
 * #1466 https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 */
public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {

	// time O(n), space O(n)
	public int minReorder(int n, int[][] connections) {
		Map<Integer, List<Integer>> in = new HashMap<>(), out = new HashMap<>();
		for(int[] c : connections){
			in.putIfAbsent(c[1], new ArrayList<>());
			out.putIfAbsent(c[0], new ArrayList<>());
			in.get(c[1]).add(c[0]);
			out.get(c[0]).add(c[1]);
		}
		int[] res = new int[1];
		boolean[] visited = new boolean[n];
		visited[0] = true;
		dfs(0, in, out, visited, res);
		return res[0];
	}

	private void dfs(int cur, Map<Integer, List<Integer>> in, Map<Integer, List<Integer>> out, boolean[] visited, int[] count){
		for(int next : in.getOrDefault(cur, new ArrayList<>())){
			if(visited[next]){
				continue;
			}
			visited[next] = true;
			dfs(next, in, out, visited, count);
		}
		for(int next : out.getOrDefault(cur, new ArrayList<>())){
			if(visited[next]){
				continue;
			}
			visited[next] = true;
			count[0]++;
			dfs(next, in, out, visited, count);
		}
	}
}
