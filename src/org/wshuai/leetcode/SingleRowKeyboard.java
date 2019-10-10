package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 8/30/2019.
 * #1165 https://leetcode.com/problems/single-row-keyboard/
 */
public class SingleRowKeyboard {
	public int calculateTime(String keyboard, String word) {
		Map<Character, Integer> map = new HashMap<>();
		int i = 0;
		for (char c : keyboard.toCharArray()) {
			map.put(c, i++);
		}
		int time = 0;
		int k = 0;
		int j = 0;
		while (j < word.length()) {
			char c = word.charAt(j);
			int v = map.get(c);
			time += v > k ? v - k : k - v;
			k = v;
			j++;
		}
		return time;
	}
}
