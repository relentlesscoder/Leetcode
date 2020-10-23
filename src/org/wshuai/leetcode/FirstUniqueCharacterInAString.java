package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0387 https://leetcode.com/problems/first-unique-character-in-a-string/
 */
public class FirstUniqueCharacterInAString {

	// time O(n)
	public int firstUniqChar(String s) {
		int[] counter = new int[26];
		for(char c : s.toCharArray()){
			counter[c - 'a']++;
		}
		for(int i = 0; i < s.length(); i++){
			if(counter[s.charAt(i) - 'a'] == 1){
				return i;
			}
		}
		return -1;
	}
}
