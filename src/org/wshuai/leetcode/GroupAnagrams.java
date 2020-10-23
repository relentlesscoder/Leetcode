package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 01/09/2020.
 * #0049 https://leetcode.com/problems/group-anagrams/
 */
public class GroupAnagrams {

	// time O(n*d), space O(n)
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for(String s : strs){
			int[] arr = new int[26];
			for(char c : s.toCharArray()){
				arr[c - 'a']++;
			}
			String key = Arrays.toString(arr);
			map.putIfAbsent(key, new ArrayList<>());
			map.get(key).add(s);
		}
		for(List<String> group : map.values()){
			res.add(group);
		}
		return res;
	}
}
