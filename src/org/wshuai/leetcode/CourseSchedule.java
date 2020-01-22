package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 12/04/2016.
 * #0207 https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {
	// time O(V + E)
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		List<Integer>[] adj = new ArrayList[numCourses];
		int[] indegree = new int[numCourses];
		int taken = 0;
		for(int i = 0; i < numCourses; i++){
			adj[i] = new ArrayList<>();
		}
		for(int[] edge : prerequisites){
			adj[edge[1]].add(edge[0]);
			indegree[edge[0]]++;
		}
		LinkedList<Integer> queue = new LinkedList<>();
		for(int i = 0; i < numCourses; i++){
			if(indegree[i] == 0){
				queue.offerLast(i);
			}
		}
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			taken++;
			for(int next : adj[cur]){
				if(--indegree[next] == 0){
					queue.offerLast(next);
				}
			}
		}
		return taken == numCourses;
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
			if(!dfs(i, adj, visited)){
				return false;
			}
		}
		return true;
	}

	private boolean dfs(int i, List<Integer>[] adj, int[] visited){
		visited[i] = 1;

		for(int next : adj[i]){
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
