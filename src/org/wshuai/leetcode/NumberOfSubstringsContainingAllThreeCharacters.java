package org.wshuai.leetcode;

/**
 * Created by Wei on 02/22/2020.
 * #1358 https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 */
public class NumberOfSubstringsContainingAllThreeCharacters {
	// time O(n)
	public int numberOfSubstrings(String s) {
		int[] count = new int[3];
		int res = 0, i = 0, j = 0, n = s.length();
		while(j < n){
			count[s.charAt(j) - 'a']++;
			while(i < j && count[0] > 0 && count[1] > 0 && count[2] > 0){
				res += (n - j);
				count[s.charAt(i++) - 'a']--;
			}
			j++;
		}
		return res;
	}
}
