package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 12/3/16.
 * #30 https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 */
public class SubstringWithConcatenationOfAllWords {
	//O(n), sliding window. https://segmentfault.com/a/1190000002625580
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> lst = new ArrayList<Integer>();
		if (s == null || s.isEmpty() || words == null || words.length == 0) {
			return lst;
		}
		Map<String, Integer> dic = new HashMap<String, Integer>();
		for (String val : words) {
			if (dic.containsKey(val)) {
				int cnt = dic.get(val);
				dic.put(val, cnt + 1);
			} else {
				dic.put(val, 1);
			}
		}
		int wLen = words[0].length();
		int dLen = words.length;
		int len = s.length();
		for (int i = 0; i < wLen; i++) {
			Map<String, Integer> curr = new HashMap<String, Integer>();
			int left = i;
			int count = 0;
			for (int j = i; j <= len - wLen; j += wLen) {
				String str = s.substring(j, j + wLen);
				if (dic.containsKey(str)) {
					if (curr.containsKey(str)) {
						curr.put(str, curr.get(str) + 1);
					} else {
						curr.put(str, 1);
					}
					if (curr.get(str) <= dic.get(str)) {
						count++;
					} else {
						while (true) {
							String tmp = s.substring(left, left + wLen);
							curr.put(tmp, curr.get(tmp) - 1);
							left += wLen;
							if (tmp.equals(str)) {
								break;
							} else {
								count--;
							}
						}
					}
					if (count == dLen) {
						lst.add(left);
						String tmp = s.substring(left, left + wLen);
						curr.put(tmp, curr.get(tmp) - 1);
						left += wLen;
						count--;
					}
				} else {
					curr.clear();
					count = 0;
					left = j + wLen;
				}
			}
		}
		return lst;
	}

	//O(n^2), using hash map
	public List<Integer> findSubstringNaive(String s, String[] words) {
		List<Integer> lst = new ArrayList<Integer>();
		if (s == null || s.isEmpty() || words == null || words.length == 0) {
			return lst;
		}
		Map<String, Integer> dic = new HashMap<String, Integer>();
		Map<String, Integer> curr = new HashMap<String, Integer>();
		for (String val : words) {
			if (dic.containsKey(val)) {
				int cnt = dic.get(val);
				dic.put(val, cnt + 1);
			} else {
				dic.put(val, 1);
			}
		}
		int wLen = words[0].length();
		int dLen = words.length;
		int len = s.length();
		for (int i = 0; i <= len - wLen * dLen; i++) {
			curr.clear();
			boolean valid = true;
			for (int j = i; j < i + wLen * dLen; j += wLen) {
				String str = s.substring(j, j + wLen);
				if (!dic.containsKey(str)) {
					valid = false;
					break;
				}
				int cnt1 = dic.get(str);
				if (!curr.containsKey(str)) {
					curr.put(str, 1);
				} else {
					int cnt2 = curr.get(str);
					cnt2++;
					if (cnt2 > cnt1) {
						valid = false;
						break;
					}
					curr.put(str, cnt2);
				}
			}
			if (valid && curr.size() == dic.size()) {
				lst.add(i);
			}
		}
		return lst;
	}
}
