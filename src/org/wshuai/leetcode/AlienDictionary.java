package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/04/2017.
 * #0269 https://leetcode.com/problems/alien-dictionary/
 */
public class AlienDictionary {
	// time O(V + E)
	// graph topological sort
	public String alienOrder(String[] words) {
		if(words == null || words.length == 0){
			return "";
		}
		if(words.length == 1){
			return words[0];
		}
		Map<Character, Set<Character>> adj = new HashMap<>();
		Map<Character, Integer> indegree = new HashMap<>();
		for(int i = 0; i < words.length - 1; i++){
			char[] s = words[i].toCharArray(), t = words[i + 1].toCharArray();
			int j = 0, k = 0;
			boolean set = false;
			while(j < s.length || k < t.length){
				if(j < s.length && k < t.length){
					adj.putIfAbsent(s[j], new HashSet<>());
					adj.putIfAbsent(t[k], new HashSet<>());
					if(s[j] != t[k] && !set){
						if(!adj.get(s[j]).contains(t[k])){
							adj.get(s[j]).add(t[k]);
							indegree.put(t[k], indegree.getOrDefault(t[k], 0) + 1);
						}
						set = true;
					}
				}else if(j < s.length){
					adj.putIfAbsent(s[j], new HashSet<>());
				}else{
					adj.putIfAbsent(t[k], new HashSet<>());
				}
				j++;
				k++;
			}
		}
		StringBuilder sb = new StringBuilder();
		LinkedList<Character> queue = new LinkedList<>();
		for(char c : adj.keySet()){
			if(indegree.getOrDefault(c, 0) == 0){
				queue.offerLast(c);
			}
		}
		while(!queue.isEmpty()){
			char cur = queue.pollFirst();
			sb.append(cur);
			for(char next : adj.get(cur)){
				int d = indegree.getOrDefault(next, 0) - 1;
				if(d == 0){
					queue.offer(next);
					indegree.remove(next);
				}else{
					indegree.put(next, d);
				}
			}
		}
		return indegree.size() == 0 ? sb.toString() : "";
	}
}
