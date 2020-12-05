package org.wshuai.leetcode;

/**
 * Created by Wei on 12/05/2020.
 * #1662 https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
 */
public class CheckIfTwoStringArraysAreEquivalent {

	// time O(n)
	public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
		int i = 0, j = 0;
		for(int s = 0, t = 0; i < word1.length && j < word2.length; ){
			char c1 = word1[i].charAt(s++), c2 = word2[j].charAt(t++);
			if(c1 != c2){
				return false;
			}
			if(s == word1[i].length()){
				s = 0;
				i++;
			}
			if(t == word2[j].length()){
				t = 0;
				j++;
			}
		}
		return i == word1.length && j == word2.length;
	}
}
