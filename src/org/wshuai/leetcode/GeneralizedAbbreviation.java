package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/16.
 * #320 https://leetcode.com/problems/generalized-abbreviation/
 */
public class GeneralizedAbbreviation {
	private List<String> lst;
	private int len;

	public List<String> generateAbbreviations(String word) {
		lst = new ArrayList<>();
		if (word == null || word.length() == 0) {
			lst.add("");
			return lst;
		}
		len = word.length();
		generateAbbreviationsUtil(word, "", 0);
		return lst;
	}

	private void generateAbbreviationsUtil(String word, String curr, int start) {
		lst.add(curr + word.substring(start));
		if (start == len) {
			return;
		}
		// i is the current starting position
		int i = start > 0 ? start + 1 : 0;
		for (; i < len; i++) {
			// get prefix from current value - "1" -> "1o" -> "1or"
			String prefix = curr + word.substring(start, i);
			// j is the characters that replaced by the count
			for (int j = 1; j <= len - i; j++) {
				//System.out.println(String.format("i:%d, j:%d, prefix: %s, curr: %s, start: %d, result: %s", i, j, prefix, prefix+j, i+j, prefix+j + word.substring(i+j)));
				generateAbbreviationsUtil(word, prefix + j, i + j);
			}
		}
	}
}
