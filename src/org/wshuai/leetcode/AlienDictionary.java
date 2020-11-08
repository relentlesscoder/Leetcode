package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/04/2017.
 * #0269 https://leetcode.com/problems/alien-dictionary/
 */
public class AlienDictionary {

	// time O(V + E)
	public String alienOrder(String[] words) {
		if(words == null || words.length == 0){
			return "";
		}
		StringBuilder res = new StringBuilder();
		Map<Character, Set<Character>> adj = new HashMap<>();
		Map<Character, Integer> degree = new HashMap<>();
		for(String s: words){
			for(char c: s.toCharArray()){
				degree.put(c,0);
			}
		}
		for(int i = 0; i < words.length - 1; i++){
			String w1 = words[i], w2 = words[i + 1];
			// prefix should go first
			if(w1.length() > w2.length() && w1.startsWith(w2)){
				return "";
			}
			for(int j = 0, k = 0; j < w1.length() && k < w2.length(); j++, k++){
				char c1 = w1.charAt(j), c2 = w2.charAt(k);
				if(c1 != c2){
					adj.putIfAbsent(c1, new HashSet<>());
					// avoid double counting
					if(!adj.get(c1).contains(c2)){
						adj.get(c1).add(c2);
						degree.put(c2, degree.getOrDefault(c2, 0) + 1);
					}
					break;
				}
			}
		}
		// graph topological sort
		LinkedList<Character> queue = new LinkedList<>();
		for(Map.Entry<Character, Integer> entry : degree.entrySet()){
			if(entry.getValue() == 0){
				queue.offerLast(entry.getKey());
			}
		}
		while(!queue.isEmpty()){
			char cur = queue.pollFirst();
			res.append(cur);
			if(!adj.containsKey(cur)){
				continue;
			}
			for(Character next : adj.get(cur)){
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
