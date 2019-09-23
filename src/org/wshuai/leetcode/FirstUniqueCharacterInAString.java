package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/19/2016.
 */
public class FirstUniqueCharacterInAString {
	public int firstUniqChar(String s) {
		if (s == null) {
			throw new IllegalArgumentException("Invalid input.");
		}
		char[] arr = s.toCharArray();
		int len = arr.length;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < len; i++) {
			char k = arr[i];
			if (map.containsKey(k)) {
				int c = map.get(k);
				map.put(k, c + 1);
			} else {
				map.put(k, 1);
			}
		}

		for (int i = 0; i < len; i++) {
			char k = arr[i];
			int c = map.get(k);
			if (c == 1) {
				return i;
			}
		}

		return -1;
	}
}
