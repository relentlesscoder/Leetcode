package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/30/2019.
 * #0582 https://leetcode.com/problems/kill-process/
 */
public class KillProcess {
	// time O(n)
	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
		List<Integer> res = new ArrayList<>();
		Map<Integer, List<Integer>> adj = new HashMap<>();
		for(int i = 0; i < pid.size(); i++){
			int parentId = ppid.get(i);
			if(parentId != 0){
				adj.putIfAbsent(parentId, new ArrayList<>());
				adj.get(parentId).add(pid.get(i));
			}
		}
		dfs(kill, adj, res);
		return res;
	}

	private void dfs(int pid, Map<Integer, List<Integer>> adj, List<Integer> res){
		res.add(pid);
		if(adj.containsKey(pid)){
			for(int childId : adj.get(pid)){
				dfs(childId, adj, res);
			}
		}
	}
}
