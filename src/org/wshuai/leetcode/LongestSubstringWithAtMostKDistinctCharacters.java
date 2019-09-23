package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/18/2016.
 * #340 https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
	//O(n), sliding window
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || s.isEmpty() || k <= 0) {
			return 0;
		}
		int left = 0;
		int max = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			char val = s.charAt(i);
			if (map.containsKey(val)) {
				int cnt = map.get(val);
				map.put(val, cnt + 1);
			} else {
				map.put(val, 1);
			}
			while (map.size() > k) {
				char lval = s.charAt(left);
				int cnt = map.get(lval);
				if (cnt == 1) {
					map.remove(lval);
				} else {
					map.put(lval, cnt - 1);
				}
				left++;
			}
			int cLen = i - left + 1;
			max = cLen > max ? cLen : max;
		}
		return max;
	}
}
