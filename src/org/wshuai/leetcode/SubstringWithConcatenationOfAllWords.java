package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 12/03/2016.
 * #0030 https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 */
public class SubstringWithConcatenationOfAllWords {
	// time O(wl * n), space O(w + n)
	// time complexity of O(wordLen * Slen) as it is O(Slen/wordLen) for the loop,
	// then O(wordLen) for substr, then also O(wordLen) for the first loop, in
	// total O(wordLen * Slen)
	// https://segmentfault.com/a/1190000002625580
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if(s.length() == 0 || words.length == 0){
			return res;
		}
		int stringLength = s.length();
		int wordSize = words[0].length();
		Map<String, Integer> wordsCount = new HashMap<>();
		int total = 0;
		for(String w : words){
			wordsCount.put(w, wordsCount.getOrDefault(w, 0) + 1);
			total++;
		}
		for(int i = 0; i < wordSize; i++){
			int start = i;
			int matched = 0;
			Map<String, Integer> currentCount = new HashMap<>();
			for(int j = i; j <= stringLength - wordSize; j += wordSize){
				String cur = s.substring(j, j + wordSize);
				if(wordsCount.containsKey(cur)){
					currentCount.put(cur, currentCount.getOrDefault(cur, 0) + 1);
					if(currentCount.get(cur) <= wordsCount.get(cur)){
						matched++;
					}else{
						while(true){
							String temp = s.substring(start, start + wordSize);
							start += wordSize;
							currentCount.put(temp, currentCount.get(temp) - 1);
							if(temp.equals(cur)){
								break;
							}else{
								matched--;
							}
						}
					}
					if(matched == total){
						res.add(start);
						String currentHead = s.substring(start, start + wordSize);
						currentCount.put(currentHead, currentCount.get(currentHead) - 1);
						start += wordSize;
						matched--;
					}
				}else{
					currentCount.clear();
					matched = 0;
					start = j + wordSize;
				}
			}
		}
		return res;
	}

	//O(n^3), using hash map
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
