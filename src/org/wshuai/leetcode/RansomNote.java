package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0383 https://leetcode.com/problems/ransom-note/
 */
public class RansomNote {
	// time O(n)
	public boolean canConstruct(String ransomNote, String magazine) {
		int[] count = new int[26];
		for(char c : magazine.toCharArray()){
			count[c - 'a']++;
		}
		for(char c : ransomNote.toCharArray()){
			int key = c - 'a';
			count[key]--;
			if(count[key] < 0){
				return false;
			}
		}
		return true;
	}
}
