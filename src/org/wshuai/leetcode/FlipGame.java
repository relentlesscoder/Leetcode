package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/19/2016.
 */
public class FlipGame {
	public List<String> generatePossibleNextMoves(String s) {
		List<String> lst = new ArrayList<String>();
		if (s == null || s.isEmpty()) {
			return lst;
		}
		char[] chars = s.toCharArray();
		int len = chars.length;

		for (int i = 0; i < len - 1; i++) {
			if (chars[i] == '+' && chars[i + 1] == '+') {
				String x = s.substring(0, i) + "--";
				if (i + 2 < len) {
					x += s.substring(i + 2);
				}
				lst.add(x);
			}
		}

		return lst;
	}
}
