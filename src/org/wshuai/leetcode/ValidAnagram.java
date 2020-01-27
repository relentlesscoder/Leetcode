package org.wshuai.leetcode;

/**
 * Created by Wei on 08/28/2016.
 * #0242 https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {
	// time O(n)
	public boolean isAnagram(String s, String t) {
		int n1 = s.length(), n2 = t.length();
		if(n1 != n2){
			return false;
		}
		int[] c1 = new int[256], c2 = new int[256];
		for(char c : s.toCharArray()){
			c1[c]++;
		}
		for(char c : t.toCharArray()){
			c2[c]++;
		}
		for(int i = 0; i < 256; i++){
			if(c1[i] != c2[i]){
				return false;
			}
		}
		return true;
	}
}
