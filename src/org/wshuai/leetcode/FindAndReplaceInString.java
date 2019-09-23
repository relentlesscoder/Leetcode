package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/19/19.
 * #833 https://leetcode.com/problems/find-and-replace-in-string/
 */
public class FindAndReplaceInString {
	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
		if (indexes.length == 0) {
			return S;
		}
		Map<Integer, String[]> map = new HashMap<>();
		for (int x = 0; x < indexes.length; x++) {
			map.put(indexes[x], new String[]{sources[x], targets[x]});
		}
		StringBuilder sb = new StringBuilder();
		int i = 0;
		char[] arr = S.toCharArray();
		while (i < arr.length) {
			if (map.containsKey(i)) {
				char[] source = map.get(i)[0].toCharArray();
				int m = i;
				int n = 0;
				while (m < arr.length && n < source.length && arr[m] == source[n]) {
					m++;
					n++;
				}
				if (n == source.length) {
					sb.append(map.get(i)[1]);
					i = m;
					continue;
				}
			}
			sb.append("" + arr[i]);
			i++;
		}
		return sb.toString();
	}
}
