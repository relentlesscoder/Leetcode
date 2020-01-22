package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2016.
 * #0186 https://leetcode.com/problems/reverse-words-in-a-string-ii/
 */
public class ReverseWordsInAStringII {
	// time O(n), space O(1)
	public void reverseWords(char[] s) {
		if(s == null || s.length == 0){
			return;
		}
		int n = s.length, i = 0, j = 0;
		reverse(s, 0, n - 1);
		while(j < n){
			if(s[j] == ' '){
				reverse(s, i, j - 1);
				i = j + 1;
			}
			j++;
		}
		reverse(s, i, j - 1);
	}

	private void reverse(char[] s, int i, int j){
		while(i < j){
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			i++;
			j--;
		}
	}
}
