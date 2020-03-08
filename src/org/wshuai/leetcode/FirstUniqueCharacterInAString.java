package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0387 https://leetcode.com/problems/first-unique-character-in-a-string/
 */
public class FirstUniqueCharacterInAString {
	// time O(n)
	public int firstUniqChar(String s) {
		int[] count = new int[26];
		char[] chars = s.toCharArray();
		for(char c : chars){
			count[c - 'a']++;
		}
		for(int i = 0; i < chars.length; i++){
			if(count[chars[i] - 'a'] == 1){
				return i;
			}
		}
		return -1;
	}
}
