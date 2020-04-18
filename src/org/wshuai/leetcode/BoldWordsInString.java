package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2019.
 * #0758 https://leetcode.com/problems/bold-words-in-string/
 */
public class BoldWordsInString {
	// time O(n*k), space O(n)
	// same as #616
	public String boldWords(String[] words, String S) {
		int n = S.length();
		boolean[] bold = new boolean[n];
		for(String word : words){
			int start = 0, index = 0, len = word.length();
			while((index = S.indexOf(word, start)) != -1){
				for(int i = index; i < index + len; i++){
					bold[i] = true;
				}
				start = index + 1;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0, j = 0; i < n; i++){
			if(!bold[i]){
				sb.append(S.charAt(i));
				continue;
			}
			j = i;
			while(j < n && bold[j]){
				j++;
			}
			sb.append("<b>" + S.substring(i, j) + "</b>");
			i = j - 1;
		}
		return sb.toString();
	}
}
