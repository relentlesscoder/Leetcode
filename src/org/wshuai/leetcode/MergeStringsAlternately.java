package org.wshuai.leetcode;

/**
 * Created by Wei on 02/24/2021.
 * #1768 https://leetcode.com/problems/merge-strings-alternately/
 */
public class MergeStringsAlternately {

	// time O(n)
	public String mergeAlternately(String word1, String word2) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(; i < word1.length() && i < word2.length(); i++){
			sb.append(word1.charAt(i));
			sb.append(word2.charAt(i));
		}
		for(; i < word1.length(); i++){
			sb.append(word1.charAt(i));
		}
		for(; i < word2.length(); i++){
			sb.append(word2.charAt(i));
		}
		return sb.toString();
	}
}
