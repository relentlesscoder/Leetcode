package org.wshuai.leetcode;

/**
 * Created by Wei on 02/12/2020.
 * #1347 https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
 */
public class MinimumNumberOfStepsToMakeTwoStringsAnagram {
	// time O(n)
	public int minSteps(String s, String t) {
		if(s.equals(t)){
			return 0;
		}
		int[] count = new int[26];
		for(char c : s.toCharArray()){
			count[c - 'a']++;
		}
		for(char c : t.toCharArray()){
			if(count[c - 'a'] > 0){
				count[c - 'a']--;
			}
		}
		int res = 0;
		for(int i = 0; i < 26; i++){
			res += count[i];
		}
		return res;
	}
}
