package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 8/9/19.
 * #884 https://leetcode.com/problems/uncommon-words-from-two-sentences/
 */
public class UncommonWordsFromTwoSentences {
	public String[] uncommonFromSentences(String A, String B) {
		List<String> res = new ArrayList<String>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] arr1 = A.split(" ");
		String[] arr2 = B.split(" ");
		for (String str : arr1) {
			map.put(str, map.getOrDefault(str, 0) + 1);
		}
		for (String str : arr2) {
			map.put(str, map.getOrDefault(str, 0) + 1);
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				res.add(entry.getKey());
			}
		}
		return res.toArray(new String[res.size()]);
	}
}
