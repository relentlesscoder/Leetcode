package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #0791 https://leetcode.com/problems/custom-sort-string/
 */
public class CustomSortString {

	// time O(m + n)
	public String customSortString(String S, String T) {
		int[] charCount = new int[26];
		for(char c : T.toCharArray()){
			charCount[c - 'a']++;
		}
		char[] res = new char[T.length()];
		int j = 0;
		for(int i = 0; i < S.length(); i++){
			char c = S.charAt(i);
			while(charCount[c - 'a']-- > 0){
				res[j++] = c;
			}
		}
		for(int i = 0; i < 26; i++){
			while(charCount[i]-- > 0){
				res[j++] = (char)(i + 'a');
			}
		}
		return String.valueOf(res);
	}
}
