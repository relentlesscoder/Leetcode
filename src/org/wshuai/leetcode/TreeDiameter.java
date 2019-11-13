package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/13/19.
 * #1245 https://leetcode.com/problems/tree-diameter/
 */
public class TreeDiameter {
	// proof https://www.youtube.com/watch?v=2PFl93WM_ao
	public int treeDiameter(int[][] edges) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int[] e : edges){
			map.putIfAbsent(e[0], new ArrayList<Integer>());
			map.putIfAbsent(e[1], new ArrayList<Integer>());
			map.get(e[0]).add(e[1]);
			map.get(e[1]).add(e[0]);
		}
		int res = 0;
		boolean[] visited = new boolean[map.size()];
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(0);
		visited[0] = true;
		int last = -1;
		while(!queue.isEmpty()){
			int node = queue.pollFirst();
			last = node;
			for(int n : map.get(node)){
				if(!visited[n]){
					visited[n] = true;
					queue.offerLast(n);
				}
			}
		}
		Arrays.fill(visited, false);
		queue.offer(last);
		visited[last] = true;
		while(!queue.isEmpty()){
			int size = queue.size();
			res++;
			while(size-- > 0){
				int node = queue.pollFirst();
				for(int n : map.get(node)){
					if(!visited[n]){
						visited[n] = true;
						queue.offerLast(n);
					}
				}
			}
		}
		return res - 1;
	}
}
