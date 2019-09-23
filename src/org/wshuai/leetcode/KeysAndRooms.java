package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 9/17/19.
 * #841 https://leetcode.com/problems/keys-and-rooms/
 */
public class KeysAndRooms {
	private List<List<Integer>> rooms;
	private int[] visited;

	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		this.rooms = rooms;
		visited = new int[rooms.size()];
		dfs(0);
		for (int v : visited) {
			if (v == 0) {
				return false;
			}
		}
		return true;
	}

	private void dfs(int i) {
		visited[i] = 1;
		List<Integer> keys = rooms.get(i);
		for (int j = 0; j < keys.size(); j++) {
			int next = keys.get(j);
			if (visited[next] == 0) {
				dfs(next);
			}
		}
	}
}
