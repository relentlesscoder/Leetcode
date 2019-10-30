package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/24/2019.
 * #756 https://leetcode.com/problems/pyramid-transition-matrix/
 */
public class PyramidTransitionMatrix {
	private Map<String, Set<Character>> map;

	// backtracking
	public boolean pyramidTransition(String bottom, List<String> allowed) {
		map = new HashMap<>();
		for(String s: allowed){
			String key = s.substring(0, 2);
			map.putIfAbsent(key, new HashSet<>());
			map.get(key).add(s.charAt(2));
		}
		return dfs(bottom);
	}

	private boolean dfs(String s){
		if(map.containsKey(s)){
			return true;
		}
		int len = s.length();
		if(len == 2){
			return false;
		}
		LinkedList<String> next = new LinkedList<>();
		for(int i = 0; i < len - 1; i++){
			String key = s.charAt(i) + "" + s.charAt(i + 1);
			if(!map.containsKey(key)){
				return false;
			}
			Set<Character> chars = map.get(key);
			if(i == 0){
				for(char c: chars){
					next.offerLast("" + c);
				}
			}else{
				int size = next.size();
				while(size > 0){
					String curr = next.pollFirst();
					for(char c: chars){
						next.offerLast(curr + c);
					}
					size--;
				}
			}
		}
		for(String n: next){
			if(dfs(n)){
				return true;
			}
		}
		return false;
	}
}
