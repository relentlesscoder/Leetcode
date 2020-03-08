package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/21/2016.
 * #0388 https://leetcode.com/problems/longest-absolute-file-path/
 */
public class LongestAbsoluteFilePath {
	// time O(n)
	public int lengthLongestPath(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 0);
		char[] chars = input.toCharArray();
		int res = 0, n = input.length(), depth = 0;
		for (int i = 0; i < n; i++) {
			int start = i;
			// none special characters
			while (i < n && chars[i] != '\n' && chars[i] != '\t') {
				i++;
			}
			if (i >= n || chars[i] == '\n') {
				String str = input.substring(start, i);
				if (str.contains(".")) {
					// find a file
					res = Math.max(res, map.get(depth) + str.length());
				} else {
					depth++;
					// depth - 1 now stores the length of path from root to the current parent folder
					map.put(depth, map.get(depth - 1) + str.length() + 1);
				}
				// need to reset depth each time
				depth = 0;
			} else {
				// \t is seen
				depth++;
			}
		}
		return res;
	}
}
