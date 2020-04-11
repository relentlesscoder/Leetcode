package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/12/2019.
 * #0678 https://leetcode.com/problems/valid-parenthesis-string/
 */
public class ValidParenthesisString {

	// time O(n)
	// https://leetcode.com/problems/valid-parenthesis-string/discuss/107577/Short-Java-O(n)-time-O(1)-space-one-pass
	public boolean checkValidString(String s) {
		int lowerBound = 0, higherBound = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				lowerBound++;
				higherBound++;
			} else if (c == ')') {
				lowerBound -= lowerBound > 0 ? 1 : 0;
				higherBound--;
			} else {
				lowerBound -= lowerBound > 0 ? 1 : 0;
				higherBound++;
			}
			if (higherBound < 0) {
				return false;
			}
		}
		return lowerBound == 0;
	}

	Map<String, Boolean> map;

	public boolean checkValidStringDfsWithMemo(String s) {
		map = new HashMap<>();
		return dfs(0, 0, s);
	}

	private boolean dfs(int s, int cur, String str) {
		if (cur < 0) {
			return false;
		}
		if (s == str.length() && cur == 0) {
			return true;
		}
		String key = s + "|" + cur;
		if (map.containsKey(key)) {
			return map.get(key);
		}
		int score = cur;
		for (int i = s; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				score++;
			} else if (str.charAt(i) == ')') {
				score--;
				if (score < 0) {
					return false;
				}
			} else {
				for (int j = -1; j <= 1; j++) {
					if (dfs(i + 1, score + j, str)) {
						return true;
					}
				}
			}
		}
		map.put(key, score == 0);
		return map.get(key);
	}
}
