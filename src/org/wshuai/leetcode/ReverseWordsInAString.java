package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2016.
 * #0151 https://leetcode.com/problems/reverse-words-in-a-string/
 */
public class ReverseWordsInAString {
	// time O(n)
	public String reverseWords(String s) {
		if(s == null || s.isEmpty()){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		String[] strs = s.split(" ");
		for(int i = strs.length - 1; i >= 0; i--){
			if(strs[i].isEmpty()){
				continue;
			}
			sb.append(strs[i] + " ");
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
	}
}
