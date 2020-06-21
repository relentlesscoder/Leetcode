package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/17/2019.
 * #0721 https://leetcode.com/problems/accounts-merge/
 */
public class AccountsMerge {
	// time O(V + E), space O(V^2)
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		List<List<String>> res = new ArrayList<>();
		int n = accounts.size();
		boolean[][] adj = new boolean[n][n];
		Map<String, Integer> map = new HashMap<>();
		boolean[] visited = new boolean[n];
		for(int i = 0; i < n; i++){
			List<String> account = accounts.get(i);
			for(int j = 1; j < account.size(); j++){
				String email = account.get(j);
				if(map.containsKey(email)){
					adj[i][map.get(email)] = adj[map.get(email)][i] = true;
				}
				map.put(email, i);
			}
		}
		for(int i = 0; i < n; i++){
			if(!visited[i]){
				TreeSet<String> emails = new TreeSet<>();
				dfs(i, n, adj, visited, emails, accounts);
				List<String> cur = new ArrayList<>();
				cur.add(accounts.get(i).get(0));
				cur.addAll(emails);
				res.add(cur);
			}
		}
		return res;
	}

	private void dfs(int i, int n, boolean[][] adj, boolean[] visited,
	                 TreeSet<String> emails, List<List<String>> accounts){
		visited[i] = true;
		List<String> account = accounts.get(i);
		for(int j = 1; j < account.size(); j++){
			emails.add(account.get(j));
		}
		for(int j = 0; j < n; j++){
			if(!visited[j] && adj[i][j]){
				dfs(j, n, adj, visited, emails, accounts);
			}
		}
	}
}
