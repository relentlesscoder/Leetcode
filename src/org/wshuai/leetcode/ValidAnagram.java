package org.wshuai.leetcode;

/**
 * Created by Wei on 08/28/2016.
 * #0242 https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {

	// time O(n)
	public boolean isAnagram(String s, String t) {
		if(s.length() != t.length()){
			return false;
		}
		int[] counter = new int[26];
		for(int i = 0; i < s.length(); i++){
			counter[s.charAt(i) - 'a']++;
			counter[t.charAt(i) - 'a']--;
		}
		for(int i = 0; i < 26; i++){
			if(counter[i] != 0){
				return false;
			}
		}
		return true;
	}
}
