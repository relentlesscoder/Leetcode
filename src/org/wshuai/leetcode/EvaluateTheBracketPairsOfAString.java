package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/08/2023.
 * #1807 https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/
 */
public class EvaluateTheBracketPairsOfAString {

	// time O(n + m), space O(n + m)
	public String evaluate(String s, List<List<String>> knowledge) {
		StringBuilder res = new StringBuilder(), key = new StringBuilder();
		Map<String, String> map = new HashMap<>();
		for (List<String> entry : knowledge) {
			map.put(entry.get(0), entry.get(1));
		}
		int openBracket = -1;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c != '(' && c != ')') {
				if (openBracket == -1) {
					res.append(c);
				} else {
					key.append(c);
				}
			} else if (c == '(') {
				openBracket = i;
			} else {
				res.append(map.getOrDefault(key.toString(), "?"));
				key = new StringBuilder();
				openBracket = -1;
			}
		}
		return res.toString();
	}
}
