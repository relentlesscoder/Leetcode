package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0395 https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

	// time O(n)
	public int longestSubstring(String s, int k) {
		int d = 0;
		for(int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++){
			d = Math.max(d, longestSubstringWithNUniqueChars(s, k, numUniqueTarget));
		}
		return d;
	}

	private int longestSubstringWithNUniqueChars(String s, int k, int numUniqueTarget){
		int[] map = new int[26];
		int numUnique = 0, numNoLessThanK = 0, start = 0, end = 0, d = 0;
		while(end < s.length()){
			if(map[s.charAt(end) - 'a']++ == 0){
				numUnique++; // increment map[c] after this statement
			}
			if(map[s.charAt(end++) - 'a'] == k){
				numNoLessThanK++; // inc end after this statement
			}

			while(numUnique > numUniqueTarget){
				if(map[s.charAt(start) - 'a']-- == k){
					numNoLessThanK--; // decrement map[c] after this statement
				}
				if(map[s.charAt(start++) - 'a'] == 0){
					numUnique--; // inc start after this statement
				}
			}

			// if we found a string where the number of unique chars equals our target
			// and all those chars are repeated at least K times then update max
			if(numUnique == numUniqueTarget && numUnique == numNoLessThanK){
				d = Math.max(end - start, d);
			}
		}
		return d;
	}
}
