package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 03/13/2017.
 * #0291 https://leetcode.com/problems/word-pattern-ii/
 */
public class WordPatternII {
	public boolean wordPatternMatch(String pattern, String str) {
		String[] map = new String[26];
		Arrays.fill(map, "");
		return dfs(0, 0, pattern, str, map, new HashSet<String>());
	}

	private boolean dfs(int i, int j, String pattern, String str, String[] map, Set<String> used){
		if(i == pattern.length()){
			return j == str.length();
		}
		char c = pattern.charAt(i);
		String val = map[c - 'a'];
		int len = val.length();
		// next substring does not match that in the map
		if(len > 0 && (j + len > str.length()
				|| !val.equals(str.substring(j, j + len)))){
			return false;
		}
		if(len > 0){
			// matching the next substring
			return dfs(i + 1, j + len, pattern, str, map, used);
		}else{
			// new character seen, try all possible substrings
			for(int k = j; k < str.length(); k++){
				String substr = str.substring(j, k + 1);
				if(used.contains(substr)){
					continue;
				}
				used.add(substr);
				map[c - 'a'] = substr;
				if(dfs(i + 1, k + 1, pattern, str, map, used)){
					return true;
				}
				used.remove(substr);
				map[c - 'a'] = "";
			}
		}
		return false;
	}
}
