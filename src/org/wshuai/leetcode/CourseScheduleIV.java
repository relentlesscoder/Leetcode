package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 05/31/2020.
 * #1462 https://leetcode.com/problems/course-schedule-iv/
 */
public class CourseScheduleIV {
	public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
		int[] ind = new int[n];
		Map<Integer, Set<Integer>> adj = new HashMap<>();
		Map<Integer, Set<Integer>> pred = new HashMap<>();
		for(int i = 0; i < prerequisites.length; i++){
			int[] p = prerequisites[i];
			adj.putIfAbsent(p[0], new HashSet<>());
			adj.get(p[0]).add(p[1]);
			ind[p[1]]++;
		}
		LinkedList<Integer> queue = new LinkedList<>();
		for(int i = 0; i < n; i++){
			if(ind[i] == 0){
				queue.offerLast(i);
				pred.putIfAbsent(i, new HashSet<>());
			}
		}
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int cur = queue.pollFirst();
				if(!adj.containsKey(cur)){
					continue;
				}
				for(int next : adj.get(cur)){
					pred.putIfAbsent(next, new HashSet<>());
					pred.get(next).add(cur);
					pred.get(next).addAll(pred.get(cur));
					if(--ind[next] == 0){
						queue.offerLast(next);
					}
				}
			}
		}
		List<Boolean> res = new ArrayList<>();
		for(int[] q : queries){
			res.add(pred.containsKey(q[1]) && pred.get(q[1]).contains(q[0]));
		}
		return res;
	}
}
