package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 12/04/2016.
 * #0210 https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseScheduleII {
	// time O(V + E)
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		List<Integer>[] adj = new ArrayList[numCourses];
		int[] res = new int[numCourses], indegree = new int[numCourses];
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
		int k = 0;
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			res[k++] = cur;
			for(int next : adj[cur]){
				if(--indegree[next] == 0){
					queue.offerLast(next);
				}
			}
		}
		return k == numCourses ? res : new int[0];
	}
}
