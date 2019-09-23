package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 12/4/16.
 * #210 https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseScheduleII {
	public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0 || prerequisites == null) {
			return new int[0];
		}
		int len = prerequisites.length;
		List<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[numCourses];
		for (int i = 0; i < len; i++) {
			int[] arr = prerequisites[i];
			int idx = arr[1];
			if (adj[idx] == null) {
				adj[idx] = new ArrayList<Integer>();
			}
			adj[idx].add(arr[0]);
		}
		Stack<Integer> stack = new Stack<Integer>();
		int[] aux = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			if (!findOrderUtil(i, adj, aux, stack)) {
				return new int[0];
			}
		}
		int[] res = new int[numCourses];
		int i = 0;
		while (!stack.isEmpty()) {
			res[i++] = stack.pop();
		}
		return res;
	}

	private boolean findOrderUtil(int i, List<Integer>[] adj, int[] aux, Stack<Integer> stack) {
		if (aux[i] == -1) {
			return false;
		}
		if (aux[i] == 1) {
			return true;
		}
		aux[i] = -1;
		if (adj[i] != null) {
			for (int j : adj[i]) {
				if (!findOrderUtil(j, adj, aux, stack)) {
					return false;
				}
			}
		}
		aux[i] = 1;
		stack.add(i);
		return true;
	}
}
