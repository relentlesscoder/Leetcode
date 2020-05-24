package org.wshuai.leetcode;

/**
 * Created by Wei on 05/24/2020.
 * #1456 https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 */
public class MaximumNumberOfVowelsInASubstringOfGivenLength {

	// time O(n)
	public int maxVowels(String s, int k) {
		int max = 0, cur = 0;
		for(int i = 0, j = 0; j < s.length(); j++){
			while(j - i + 1 > k){
				if(isVowel(s.charAt(i++))){
					cur--;
				}
			}
			cur += isVowel(s.charAt(j)) ? 1 : 0;
			if(j - i + 1 == k){
				max = Math.max(max, cur);
			}
		}
		return max;
	}

	private boolean isVowel(char c){
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
