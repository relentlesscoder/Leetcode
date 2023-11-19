package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/17/2019.
 * #0721 https://leetcode.com/problems/accounts-merge/
 */
public class AccountsMerge {

	// complexity analysis - https://leetcode.com/problems/accounts-merge/solution/
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		List<List<String>> res = new ArrayList<>();
		int n = accounts.size();
		boolean[][] adj = new boolean[n][n];
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			List<String> vals = accounts.get(i);
			for(int j = 1; j < vals.size(); j++){
				String email = vals.get(j);
				if(map.containsKey(email)){
					adj[map.get(email)][i] = adj[i][map.get(email)] = true;
				}
				map.put(email, i);
			}
		}
		boolean[] visited = new boolean[n];
		for(int i = 0; i < n; i++){
			if(visited[i]){
				continue;
			}
			TreeSet<String> emails = new TreeSet<>();
			dfs(i, n, accounts, adj, emails, visited);
			List<String> cur = new ArrayList<>();
			cur.add(accounts.get(i).get(0));
			cur.addAll(emails);
			res.add(cur);
		}
		return res;
	}

	private void dfs(int i, int n, List<List<String>> accounts,
					 boolean[][] adj, TreeSet<String> emails, boolean[] visited){
		visited[i] = true;
		List<String> account = accounts.get(i);
		for(int k = 1; k < account.size(); k++){
			emails.add(account.get(k));
		}
		for(int j = 0; j < n; j++){
			if(adj[i][j] && !visited[j]){
				dfs(j, n, accounts, adj, emails, visited);
			}
		}
	}

	public List<List<String>> accountsMergeUnionFind(List<List<String>> accounts) {
		List<List<String>> res = new ArrayList<>();
		Map<String, String> root = new HashMap<>();
		Map<String, String> users = new HashMap<>();
		Map<String, TreeSet<String>> union = new HashMap<>();
		for(List<String> account : accounts){
			String username = account.get(0);
			for(int i = 1; i < account.size(); i++){
				root.put(account.get(i), account.get(i));
				users.put(account.get(i), username);
			}
		}
		for(List<String> account : accounts){
			String p = find(account.get(1), root);
			for(int i = 2; i < account.size(); i++){
				root.put(find(account.get(i), root), p);
			}
		}
		for(List<String> account : accounts){
			String p = find(account.get(1), root);
			union.putIfAbsent(p, new TreeSet<>());
			for(int i = 1; i < account.size(); i++){
				union.get(p).add(account.get(i));
			}
		}
		for(String p : union.keySet()){
			List<String> emails = new ArrayList<>();
			emails.add(users.get(p));
			emails.addAll(union.get(p));
			res.add(emails);
		}
		return res;
	}

	private String find(String r, Map<String, String> root){
		if(!r.equals(root.get(r))){
			root.put(r, find(root.get(r), root));
		}
		return root.get(r);
	}

    /*
    private String find(String r, Map<String, String> root){
        while(!r.equals(root.get(r))) {
            //path compression
            root.put(r, root.get(root.get(r)));
            r = root.get(r);
        }

        return r;
    }*/
}
