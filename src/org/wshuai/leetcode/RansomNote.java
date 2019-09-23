package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/19/2016.
 */
public class RansomNote {
	public boolean canConstruct(String ransomNote, String magazine) {
		if (ransomNote == null || magazine == null) {
			throw new IllegalArgumentException("Invalid input.");
		}
		char[] arr1 = ransomNote.toCharArray();
		char[] arr2 = magazine.toCharArray();
		int len1 = arr1.length;
		int len2 = arr2.length;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < len2; i++) {
			char k = arr2[i];
			if (map.containsKey(k)) {
				int c = map.get(k);
				map.put(k, c + 1);
			} else {
				map.put(k, 1);
			}
		}
		for (int i = 0; i < len1; i++) {
			char k = arr1[i];
			Integer c = map.get(k);
			if (c == null) {
				return false;
			}
			int x = (int) c;
			if (x == 0) {
				return false;
			}
			map.put(k, x - 1);
		}
		return true;
	}
}
