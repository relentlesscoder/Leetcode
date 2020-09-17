package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 09/21/2016.
 * #0249 https://leetcode.com/problems/group-shifted-strings/
 */
public class GroupShiftedStrings {

	// time O(n), space O(n)
	public List<List<String>> groupStrings(String[] strings) {
		Map<String, List<String>> map = new HashMap<>();
		for(String s : strings){
			String t = convert(s);
			map.putIfAbsent(t, new ArrayList<>());
			map.get(t).add(s);
		}
		List<List<String>> res = new ArrayList<>();
		for(List<String> val : map.values()){
			res.add(val);
		}
		return res;
	}

	private String convert(String s){
		StringBuilder sb = new StringBuilder();
		int diff = s.charAt(0) - 'a';
		for(char c : s.toCharArray()){
			char t = (char)(c - diff);
			t = t < 'a' ? (char)(t + 26) : t;
			sb.append(t);
		}
		return sb.toString();
	}
}
