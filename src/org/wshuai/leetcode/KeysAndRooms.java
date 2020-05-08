package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 09/17/2019.
 * #0841 https://leetcode.com/problems/keys-and-rooms/
 */
public class KeysAndRooms {

	// time O(n)
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		int n = rooms.size();
		boolean[] visited = new boolean[n];
		dfs(0, rooms, visited);
		for(boolean v : visited){
			if(!v){
				return false;
			}
		}
		return true;
	}

	private void dfs(int i, List<List<Integer>> rooms, boolean[] visited){
		visited[i] = true;
		for(int next : rooms.get(i)){
			if(!visited[next]){
				dfs(next, rooms, visited);
			}
		}
	}
}
