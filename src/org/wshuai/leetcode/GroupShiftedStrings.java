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

	// time O(n * l), space O(n * l)
	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> res = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String s : strings) {
			char[] arr = s.toCharArray();
			int offset = arr[0] - 'a';
			arr[0] = 'a';
			for (int i = 1; i < arr.length; i++) {
				arr[i] = (char)((arr[i] - offset + 26) % 26);
			}
			String key = String.valueOf(arr);
			map.putIfAbsent(key, new ArrayList<>());
			map.get(key).add(s);
		}
		for (List<String> list : map.values()) {
			res.add(list);
		}
		return res;
	}
}
