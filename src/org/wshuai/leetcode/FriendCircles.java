package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 09/09/2019.
 * #0547 https://leetcode.com/problems/friend-circles/
 */
public class FriendCircles {

	// time O(n)
	public int findCircleNumDFS(int[][] M) {
		int res = 0, n = M.length;
		boolean[] visited = new boolean[n];
		for(int i = 0; i < n; i++){
			if(!visited[i]){
				dfs(i, visited, M);
				res++;
			}
		}
		return res;
	}

	private void dfs(int cur, boolean[] visited, int[][] M){
		visited[cur] = true;
		for(int i = 0; i < M.length; i++){
			if(M[cur][i] == 1 && !visited[i]){
				dfs(i, visited, M);
			}
		}
	}

	// time O(n^2), space O(n)
	public int findCircleNumUnionFind(int[][] M) {
		int n = M.length, res = n;
		int[] root = new int[n];
		for(int i = 0; i < n; i++){
			root[i] = i;
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i != j && M[i][j] == 1){
					int r1 = findRoot(i, root), r2 = findRoot(j, root);
					if(r1 == r2){
						continue;
					}
					root[r2] = r1;
					res--;
				}
			}
		}
		return res;
	}

	private int findRoot(int r, int[] root){
		if(r != root[r]){
			root[r] = findRoot(root[r], root);
		}
		return root[r];
	}

	// time O(n), space O(n)
	public int findCircleNumBFS(int[][] M) {
		int[] visited = new int[M.length];
		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < M.length; i++) {
			if (visited[i] == 0) {
				queue.add(i);
				while (!queue.isEmpty()) {
					int s = queue.remove();
					visited[s] = 1;
					for (int j = 0; j < M.length; j++) {
						if (M[s][j] == 1 && visited[j] == 0) {
							queue.add(j);
						}
					}
				}
				count++;
			}
		}
		return count;
	}
}
