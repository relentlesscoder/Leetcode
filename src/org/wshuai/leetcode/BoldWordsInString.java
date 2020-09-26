package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2019.
 * #0758 https://leetcode.com/problems/bold-words-in-string/
 */
public class BoldWordsInString {

	// time O(k*n^2), space O(n)
	// same as #616
	public String boldWords(String[] words, String S) {
		int n = S.length();
		boolean[] bold = new boolean[n];
		for(String word : words){
			int i = 0, j;
			while((j = S.indexOf(word, i)) >= 0){
				for(int k = 0; k < word.length(); k++){
					bold[j + k] = true;
				}
				// advance one position to match entire "zzz" using pattern "zz"
				i = j + 1;
			}
		}
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < n; i++){
			if(bold[i] && (i == 0 || !bold[i - 1])){
				res.append("<b>");
			}
			res.append(S.charAt(i));
			if(bold[i] && (i == n - 1 || !bold[i + 1])){
				res.append("</b>");
			}
		}
		return res.toString();
	}
}
