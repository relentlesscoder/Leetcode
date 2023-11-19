package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 12/30/2019.
 * #1306 https://leetcode.com/problems/jump-game-iii/
 */
public class JumpGameIII {

	// BFS, time O(n), space O(n)
	public boolean canReach(int[] arr, int start) {
		int n = arr.length;
		LinkedList<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n];
		queue.offerLast(start);
		visited[start] = true;
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			if(arr[cur] == 0){
				return true;
			}
			int left = cur - arr[cur];
			int right = cur + arr[cur];
			if(left >= 0 && !visited[left]){
				visited[left] = true;
				queue.offerLast(left);
			}
			if(right < n && !visited[right]){
				visited[right] = true;
				queue.offerLast(right);
			}
		}
		return false;
	}

	// DFS, time O(n)
	public boolean canReachDFS(int[] arr, int start) {
		return dfs(arr, start, new boolean[arr.length]);
	}

	private boolean dfs(int[] arr, int i, boolean[] visited){
		if(i < 0 || i >= arr.length || visited[i]){
			return false;
		}
		visited[i] = true;
		return arr[i] == 0 || dfs(arr, i - arr[i], visited)
			|| dfs(arr, i + arr[i], visited);
	}
}
