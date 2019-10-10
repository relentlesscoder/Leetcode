package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/21/2016.
 * #388 https://leetcode.com/problems/longest-absolute-file-path/
 */
public class LongestAbsoluteFilePath {
	public int lengthLongestPath(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 0);
		int len = input.length();
		int level = 0;
		int max = 0;
		for (int i = 0; i < len; i++) {
			int s = i;
			while (i < len && input.charAt(i) != '\n' && input.charAt(i) != '\t') {
				i++;
			}
			if (i >= len || input.charAt(i) == '\n') {
				String str = input.substring(s, i);
				if (str.contains(".")) {
					max = Math.max(max, map.get(level) + str.length());
				} else {
					level++;
					map.put(level, map.get(level - 1) + str.length() + 1);
				}
				level = 0;
			} else {
				level++;
			}
		}
		return max;
	}
}
