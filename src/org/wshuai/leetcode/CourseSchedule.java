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
		for(int[] p : prerequisites){
			adj[p[1]].add(p[0]);
		}
		int[] visited = new int[numCourses];
		for(int i = 0; i < numCourses; i++){
			if(visited[i] == 0 && !dfs(i, visited, adj)){
				return false;
			}
		}
		return true;
	}

	private boolean dfs(int cur, int[] visited, List<Integer>[] adj){
		visited[cur] = 1;
		for(int next : adj[cur]){
			if(visited[next] == 1
					|| (visited[next] == 0 && !dfs(next, visited, adj))){
				return false;
			}
		}
		visited[cur] = 2;
		return true;
	}
}
