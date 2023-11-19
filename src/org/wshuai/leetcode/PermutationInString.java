package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2019.
 * #0567 https://leetcode.com/problems/permutation-in-string/
 */
public class PermutationInString {

	// time O(n)
	public boolean checkInclusion(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		if(m > n){
			return false;
		}
		int[] count = new int[26];
		for(int i = 0; i < m; i++){
			count[s1.charAt(i) - 'a']++;
			count[s2.charAt(i) - 'a']--;
		}
		if(allMatched(count)){
			return true;
		}
		for(int i = m; i < n; i++){
			count[s2.charAt(i) - 'a']--;
			count[s2.charAt(i - m) - 'a']++;
			if(allMatched(count)){
				return true;
			}
		}
		return false;
	}

	private boolean allMatched(int[] count){
		for(int i = 0; i < 26; i++){
			if(count[i] != 0){
				return false;
			}
		}
		return true;
	}
}
