package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/19/2016.
 * #0290 https://leetcode.com/problems/word-pattern/
 */
public class WordPattern {
	// time O(n)
	public boolean wordPattern(String pattern, String str) {
		String[] strs = str.split(" ");
		if(strs.length != pattern.length()){
			return false;
		}
		Map<Character, String> map = new HashMap<>();
		Set<String> mapped = new HashSet<>();
		for(int i = 0; i < strs.length; i++){
			char c = pattern.charAt(i);
			if(map.containsKey(c) && !map.get(c).equals(strs[i])){
				return false;
			}
			if(!map.containsKey(c) && !mapped.add(strs[i])){
				return false;
			}
			map.put(c, strs[i]);
		}
		return true;
	}
}
