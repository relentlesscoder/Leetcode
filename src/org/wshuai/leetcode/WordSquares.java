package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/18/19.
 * #425 https://leetcode.com/problems/word-squares/
 */
public class WordSquares {
	public List<List<String>> wordSquares(String[] words) {
		int N = words[0].length();
		Map<String, Set<String>> prefix = new HashMap<>();
		for(String word : words){
			for(int i = 1; i <= word.length(); i++){
				String str = word.substring(0, i);
				prefix.putIfAbsent(str, new HashSet<>());
				prefix.get(str).add(word);
			}
		}

		List<List<String>> res = new ArrayList<>();
		List<String> curr = null;
		for(String word : words){
			curr = new ArrayList<>();
			curr.add(word);
			dfs(1, N, curr, prefix, res);
		}
		return res;
	}

	private void dfs(int i, int N, List<String> curr, Map<String, Set<String>> prefix, List<List<String>> res){
		if(i == N){
			res.add(new ArrayList<>(curr));
			return;
		}
		StringBuilder sb = new StringBuilder();
		for(String s : curr){
			sb.append(s.charAt(i));
		}
		String p = sb.toString();
		if(!prefix.containsKey(p)){
			return;
		}
		for(String next : prefix.get(p)){
			curr.add(next);
			dfs(i + 1, N, curr, prefix, res);
			curr.remove(curr.size() - 1);
		}
	}
}
