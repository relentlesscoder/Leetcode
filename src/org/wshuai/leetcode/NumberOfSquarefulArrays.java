package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 11/16/2019.
 * #996 https://leetcode.com/problems/number-of-squareful-arrays/
 */
public class NumberOfSquarefulArrays {
	private int res;
	private Map<Integer, Integer> count;
	private Map<Integer, Set<Integer>> square;

	public int numSquarefulPerms(int[] A) {
		res = 0;
		int N = A.length;
		count = new HashMap<>();
		square = new HashMap<>();
		for(int a : A){
			square.putIfAbsent(a, new HashSet<>());
			count.put(a, count.getOrDefault(a, 0) + 1);
		}
		for(int a : count.keySet()){
			for(int b : count.keySet()){
				double sqrt = Math.sqrt(a + b);
				if(sqrt == Math.floor(sqrt)){
					square.get(a).add(b);
					square.get(b).add(a);
				}
			}
		}
		for(int i : count.keySet()){
			dfs(i, N - 1);
		}
		return res;
	}

	private void dfs(int i, int left){
		count.put(i, count.get(i) - 1);
		if(left == 0){
			res++;
		}
		if(!square.containsKey(i)){
			return;
		}
		Set<Integer> set = square.get(i);
		for(int j : set){
			if(count.get(j) != 0){
				dfs(j, left - 1);
			}
		}
		count.put(i, count.get(i) + 1);
	}
}
