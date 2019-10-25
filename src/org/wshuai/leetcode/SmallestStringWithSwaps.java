package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/25/2019.
 * #1202 https://leetcode.com/problems/smallest-string-with-swaps/
 */
public class SmallestStringWithSwaps {
	private int[] root;

	// union find
	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		int n = s.length();
		root = new int[n];
		for(int i = 0; i < n; i++){
			root[i] = i;
		}
		// use union find the group exchangeable indexes together
		for(List<Integer> p: pairs){
			int r1 = findRoot(p.get(0));
			int r2 = findRoot(p.get(1));
			root[r2] = r1;
		}
		Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			int r = findRoot(i);
			map.putIfAbsent(r, new PriorityQueue<>());
			map.get(r).offer(s.charAt(i));
		}
		char[] res = new char[n];
		// find the characters with the same root (exchangeable),
		// write to the output from the lexicographically smallest
		for(int i = 0; i < n; i++){
			res[i] = map.get(root[i]).poll();
		}
		return new String(res);
	}

	private int findRoot(int n){
		if(n != root[n]){
			root[n] = findRoot(root[n]);
		}
		return root[n];
	}
}
