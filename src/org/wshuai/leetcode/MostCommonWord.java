package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 08/09/2019.
 * #0819 https://leetcode.com/problems/most-common-word/
 */
public class MostCommonWord {
	// time O(n)
	public String mostCommonWord(String paragraph, String[] banned) {
		Set<String> ban = new HashSet<>();
		Map<String, Integer> map = new HashMap<>();
		for(String b : banned){
			ban.add(b);
		}
		String res = "";
		paragraph += "#";
		int n = paragraph.length(), max = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++){
			char c = paragraph.charAt(i);
			if(Character.isAlphabetic(c)){
				sb.append(Character.toLowerCase(c));
			}else if(sb.length() > 0){
				String cur = sb.toString();
				int count = map.getOrDefault(cur, 0) + 1;
				map.put(cur, count);
				if(count > max && !ban.contains(cur)){
					max = count;
					res = cur;
				}
				sb = new StringBuilder();
			}
		}
		return res;
	}
}
