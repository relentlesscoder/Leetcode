package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0389 https://leetcode.com/problems/find-the-difference/
 */
public class FindTheDifference {
	// time O(n)
	public char findTheDifference(String s, String t) {
		int[] count = new int[26];
		for(char c : t.toCharArray()){
			count[c - 'a']++;
		}
		for(char c : s.toCharArray()){
			count[c - 'a']--;
		}
		for(int i = 0; i < 26; i++){
			if(count[i] > 0){
				return (char)('a' + i);
			}
		}
		return ' ';
	}
}
