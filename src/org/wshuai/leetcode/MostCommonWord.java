package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/09/2019.
 * #0819 https://leetcode.com/problems/most-common-word/
 */
public class MostCommonWord {

	// time O(n)
	public String mostCommonWord(String paragraph, String[] banned) {
		String res = "";
		int max = 0;
		paragraph += "#";
		Set<String> set = new HashSet<>(Arrays.asList(banned));
		Map<String, Integer> map = new HashMap<>();
		StringBuilder cur = new StringBuilder();
		for(int i = 0; i < paragraph.length(); i++){
			char c = paragraph.charAt(i);
			if(Character.isAlphabetic(c)){
				cur.append(Character.toLowerCase(c));
			}else if(cur.length() > 0){
				String word = cur.toString();
				cur = new StringBuilder();
				if(set.contains(word)){
					continue;
				}
				int count = map.getOrDefault(word, 0) + 1;
				map.put(word, count);
				if(count > max){
					max = count;
					res = word;
				}
			}
		}
		return res;
	}
}
