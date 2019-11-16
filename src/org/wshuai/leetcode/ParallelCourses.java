package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 11/16/2019.
 * #1136 https://leetcode.com/problems/parallel-courses/
 */
public class ParallelCourses {
	// https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
	public int minimumSemesters(int N, int[][] relations) {
		boolean[] taken = new boolean[N];
		LinkedList<Integer> queue = new LinkedList<>();
		LinkedList<Integer>[] adj = new LinkedList[N];
		int[] degree = new int[N];
		for(int i = 0; i < N; i++){
			adj[i] = new LinkedList<>();
		}
		for(int[] r : relations){
			degree[r[1] - 1]++;
			adj[r[0] - 1].offerLast(r[1] - 1);
		}
		for(int i = 0; i < N; i++){
			if(degree[i] == 0){
				taken[i] = true;
				queue.offerLast(i);
			}
		}
		if(queue.size() == 0){
			return -1;
		}
		int res = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			res++;
			while(size-- > 0){
				int curr = queue.pollFirst();
				for(int next : adj[curr]){
					degree[next]--;
				}
			}
			if(queue.isEmpty()){
				for(int i = 0; i < N; i++){
					if(degree[i] == 0 && !taken[i]){
						taken[i] = true;
						queue.offerLast(i);
					}
				}
			}
		}
		for(boolean t : taken){
			if(!t){
				return -1;
			}
		}
		return res;
	}
}
