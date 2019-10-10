package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 9/19/2016.
 */
public class WordPattern {
	public boolean wordPattern(String pattern, String str) {
		if (pattern == null || str == null) {
			throw new IllegalArgumentException("Invalid input.");
		}
		char[] pArr = pattern.toCharArray();
		char[] sArr = str.toCharArray();
		int pLen = pArr.length;
		int sLen = sArr.length;
		Map<Character, String> map = new HashMap<Character, String>();
		List<String> lst = new ArrayList<String>();
		Set<String> used = new HashSet<String>();
		for (int i = 0; i < sLen; ) {
			int j = i;
			while (j < sLen && sArr[j] != ' ') {
				j++;
			}
			String val = str.substring(i, j);
			lst.add(val);
			if (j == sLen) {
				break;
			} else {
				i = j + 1;
			}
		}
		int size = lst.size();
		if (size != pLen) {
			return false;
		}

		for (int i = 0; i < pLen; i++) {
			char k = pArr[i];
			String s = lst.get(i);
			if (map.containsKey(k)) {
				String val = map.get(k);
				if (!val.equals(s)) {
					return false;
				}
			} else if (!used.contains(s)) {
				map.put(k, s);
				used.add(s);
			} else {
				return false;
			}
		}

		return true;
	}
}
