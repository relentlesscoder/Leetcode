package org.wshuai.leetcode;

/**
 * Created by Wei on 09/18/2016.
 * #0344 https://leetcode.com/problems/reverse-string/
 */
public class ReverseString {
	// time O(n)
	public void reverseString(char[] s) {
		int i = 0, j = s.length - 1;
		while(i < j){
			char temp = s[i];
			s[i++] = s[j];
			s[j--] = temp;
		}
	}
}
