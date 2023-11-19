package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 12/04/2016.
 * #0210 https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseScheduleII {

	// time O(V + E), space O(V + E)
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		List<Integer>[] adj = new ArrayList[numCourses];
		int[] degree = new int[numCourses], order = new int[numCourses];
		LinkedList<Integer> queue = new LinkedList<>();
		for(int i = 0; i < numCourses; i++){
			adj[i] = new ArrayList<>();
		}
		for(int[] p : prerequisites){
			degree[p[0]]++;
			adj[p[1]].add(p[0]);
		}
		for(int i = 0; i < numCourses; i++){
			if(degree[i] == 0){
				queue.offerLast(i);
			}
		}
		int taken = 0;
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			order[taken++] = cur;
			for(int next : adj[cur]){
				if(--degree[next] == 0){
					queue.offerLast(next);
				}
			}
		}
		return taken == numCourses ? order : new int[0];
	}
}
