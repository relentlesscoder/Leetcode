package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/22/19.
 * #1258 https://leetcode.com/problems/synonymous-sentences/
 */
public class SynonymousSentences {
	Map<Integer, TreeSet<String>> group;
	Map<String, Integer> map;

	public List<String> generateSentences(List<List<String>> synonyms, String text) {
		List<String> res = new ArrayList<>();
		if(text == null || text.isEmpty()){
			return res;
		}
		group = new HashMap<>();
		map = new HashMap<>();
		int gId = 0;
		for(List<String> s : synonyms){
			String s1 = s.get(0);
			String s2 = s.get(1);
			if(map.containsKey(s1) && map.containsKey(s2)){
				continue;
			}
			if(!map.containsKey(s1) && !map.containsKey(s2)){
				map.put(s1, gId);
				map.put(s2, gId);
				group.put(gId, new TreeSet<>());
				group.get(gId).add(s1);
				group.get(gId).add(s2);
				gId++;
			}else if(map.containsKey(s1)){
				int id = map.get(s1);
				map.put(s2, id);
				group.get(id).add(s2);
			}else{
				int id = map.get(s2);
				map.put(s1, id);
				group.get(id).add(s1);
			}
		}
		String[] words = text.split(" ");
		dfs(0, words, words.length, res, new ArrayList<>());
		return res;
	}

	private void dfs(int i, String[] words, int len, List<String> res, List<String> cur){
		if(i == len){
			res.add(buildSentence(cur));
			return;
		}
		if(!map.containsKey(words[i])){
			cur.add(words[i]);
			dfs(i + 1, words, len, res, cur);
		}else{
			TreeSet<String> set = group.get(map.get(words[i]));
			for(String s : set){
				cur.add(s);
				dfs(i + 1, words, len, res, new ArrayList<>(cur));
				cur.remove(cur.size() - 1);
			}
		}
	}

	private String buildSentence(List<String> cur){
		StringBuilder sb = new StringBuilder();
		for(String s : cur){
			sb.append(s + " ");
		}
		String sen = sb.toString();
		return sen.substring(0, sen.length() - 1);
	}
}
