package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/18/2019.
 * #0425 https://leetcode.com/problems/word-squares/
 */
public class WordSquares {
	// time O(m*n)
	public List<List<String>> wordSquares(String[] words) {
		List<List<String>> res = new ArrayList<>();
		if(words == null || words.length == 0){
			return res;
		}
		int n = words[0].length();
		Map<String, Set<String>> map = new HashMap<>();
		for(String word : words){
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < word.length(); i++){
				sb.append(word.charAt(i));
				String prefix = sb.toString();
				map.putIfAbsent(prefix, new HashSet<>());
				map.get(prefix).add(word);
			}
		}
		for(String word : words){
			List<String> cur = new ArrayList<>();
			cur.add(word);
			dfs(1, n, cur, res, map);
		}
		return res;
	}

	private void dfs(int i, int n, List<String> cur, List<List<String>> res, Map<String, Set<String>> map){
		if(i == n){
			res.add(new ArrayList<>(cur));
			return;
		}
		StringBuilder sb = new StringBuilder();
		for(String s : cur){
			sb.append(s.charAt(i));
		}
		String prefix = sb.toString();
		if(!map.containsKey(prefix)){
			return;
		}
		for(String s : map.get(prefix)){
			cur.add(s);
			dfs(i + 1, n, cur, res, map);
			cur.remove(cur.size() - 1);
		}
	}
}
