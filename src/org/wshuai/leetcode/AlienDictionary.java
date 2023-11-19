package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/04/2017.
 * #0269 https://leetcode.com/problems/alien-dictionary/
 */
public class AlienDictionary {

	// time O(V + E)
	public String alienOrder(String[] words) {
		StringBuilder res = new StringBuilder();
		Map<Character, Integer> degree = new HashMap<>();
		Map<Character, Set<Character>> adj = new HashMap<>();
		for(String str : words){
			for(char c : str.toCharArray()){
				degree.put(c, 0);
			}
		}
		for(int i = 1; i < words.length; i++){
			String prev = words[i - 1], cur = words[i];
			int j = 0;
			for(; j < prev.length() && j < cur.length(); j++){
				char c1 = prev.charAt(j), c2 = cur.charAt(j);
				if(c1 != c2){
					adj.putIfAbsent(c1, new HashSet<>());
					if(!adj.get(c1).contains(c2)){ // avoid double counting
						adj.get(c1).add(c2);
						degree.put(c2, degree.get(c2) + 1);
					}
					break;
				}
			}
			if(j < prev.length() && j == cur.length()){ // prefix should go first, [great, gre] is invalid
				return "";
			}
		}
		// graph topological sort
		LinkedList<Character> queue = new LinkedList<>();
		for(char key : degree.keySet()){
			if(degree.get(key) == 0){
				queue.offerLast(key);
			}
		}
		while(!queue.isEmpty()){
			char cur = queue.pollFirst();
			res.append(cur);
			if(!adj.containsKey(cur)){
				continue;
			}
			for(char next : adj.get(cur)){
				degree.put(next, degree.get(next) - 1);
				if(degree.get(next) == 0){
					queue.offerLast(next);
				}
			}
		}
		// detect loop
		if(res.length() != degree.size()){
			return "";
		}
		return res.toString();
	}
}
