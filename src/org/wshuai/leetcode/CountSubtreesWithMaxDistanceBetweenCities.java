package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/17/2020.
 * #1617 https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities/
 */
public class CountSubtreesWithMaxDistanceBetweenCities {

	// time O(n*(2^n)), space O(n*(2^n))
	// https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities/discuss/889070/C%2B%2BPython-Bitmask-try-all-subset-of-cities-Clean-and-Concise-O(2n-*-n)
	public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
		Map<Integer, Set<Integer>> adj = new HashMap<>();
		for(int[] e : edges){
			adj.putIfAbsent(e[0] - 1, new HashSet<>());
			adj.putIfAbsent(e[1] - 1, new HashSet<>());
			adj.get(e[0] - 1).add(e[1] - 1);
			adj.get(e[1] - 1).add(e[0] - 1);
		}
		int[] res = new int[n - 1];
		for(int i = 1; i < (1 << n); i++){
			int d = findTreeDiameter(i, n, adj);
			if(d > 0){
				res[d - 1]++;
			}
		}
		return res;
	}

	// time O(n), space O(n)
	// tree diameter #1245
	private int findTreeDiameter(int status, int n, Map<Integer, Set<Integer>> adj){
		Set<Integer> subtree = new HashSet<>();
		for(int i = 0; i < n; i++){
			if(((1 << i) & status) != 0){
				subtree.add(i);
			}
		}
		int res = 0, first = subtree.iterator().next(), last = -1, count = 0;
		boolean[] visited = new boolean[n];
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offer(first);
		visited[first] = true;
		while(!queue.isEmpty()){
			last = queue.pollFirst();
			count++;
			if(adj.containsKey(last)){
				for(int next : adj.get(last)){
					if(subtree.contains(next) && !visited[next]){
						visited[next] = true;
						queue.offerLast(next);
					}
				}
			}
		}
		// not all nodes are reachable, subtree is invalid
		if(count != subtree.size()){
			return 0;
		}
		Arrays.fill(visited, false);
		queue = new LinkedList<>();
		queue.offer(last);
		visited[last] = true;
		while(!queue.isEmpty()){
			res++;
			int size = queue.size();
			while(size-- > 0){
				int cur = queue.pollFirst();
				if(adj.containsKey(cur)){
					for(int next : adj.get(cur)){
						if(subtree.contains(next) && !visited[next]){
							visited[next] = true;
							queue.offerLast(next);
						}
					}
				}
			}
		}
		return res - 1;
	}
}
