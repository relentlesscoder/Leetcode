package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 12/04/2016.
 * #0207 https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {

	// time O(V + E), space O(V + E)
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[] inDegree = new int[numCourses];
		LinkedList<Integer> queue = new LinkedList<>();
		ArrayList<Integer>[] adj = new ArrayList[numCourses];
		for(int i = 0; i < numCourses; i++){
			adj[i] = new ArrayList<>();
		}
		for(int[] p : prerequisites){
			inDegree[p[0]]++;
			adj[p[1]].add(p[0]);
		}
		for(int i = 0; i < numCourses; i++){
			if(inDegree[i] == 0){
				queue.offerLast(i);
			}
		}
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			numCourses--;
			for(int next : adj[cur]){
				if(--inDegree[next] == 0){
					queue.offerLast(next);
				}
			}
		}
		return numCourses == 0;
	}

	// time O(V + E)
	public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
		List<Integer>[] adj = new ArrayList[numCourses];
		for(int i = 0; i < numCourses; i++){
			adj[i] = new ArrayList<>();
		}
		for(int[] edge : prerequisites){
			adj[edge[1]].add(edge[0]);
		}
		int[] visited = new int[numCourses];
		for(int i = 0; i < numCourses; i++){
			if(visited[i] == 0 && !dfs(i, adj, visited)){
				return false;
			}
		}
		return true;
	}

	private boolean dfs(int i, List<Integer>[] adj, int[] visited){
		visited[i] = 1;

		for(int next : adj[i]){
			// if node has been visited in the current dfs path before,
			// cycle is detected
			if(visited[next] == 1){
				return false;
			}
			if(visited[next] == 0){
				if(!dfs(next, adj, visited)){
					return false;
				}
			}
		}

		visited[i] = 2;
		return true;
	}
}
