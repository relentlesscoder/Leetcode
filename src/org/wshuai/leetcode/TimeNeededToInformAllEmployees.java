package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 03/08/2020.
 * #1376 https://leetcode.com/problems/time-needed-to-inform-all-employees/
 */
public class TimeNeededToInformAllEmployees {
	// time O(n)
	public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
		List<Integer>[] adj = new ArrayList[n];
		for(int i = 0; i < n; i++){
			adj[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < n; i++){
			if(manager[i] == -1){
				continue;
			}
			adj[manager[i]].add(i);
		}
		return dfs(headID, adj, informTime);
	}

	private int dfs(int cur, List<Integer>[] adj, int[] informTime){
		if(adj[cur].size() == 0){
			return 0;
		}
		int time = 0;
		for(int next : adj[cur]){
			time = Math.max(dfs(next, adj, informTime), time);
		}
		return time + informTime[cur];
	}
}
