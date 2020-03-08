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
		List<List<String>> res = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for(String s : strings){
			String key = getKey(s.toCharArray());
			map.putIfAbsent(key, new ArrayList<>());
			map.get(key).add(s);
		}
		for(List<String> list : map.values()){
			res.add(list);
		}
		return res;
	}

	private String getKey(char[] arr){
		int diff = arr[0] - 'a';
		char[] res = new char[arr.length];
		for(int i = 0; i < arr.length; i++){
			int cur = (arr[i] - diff + 26) % 26;
			res[i] = (char)('a' + cur);
		}
		return new String(res);
	}
}
