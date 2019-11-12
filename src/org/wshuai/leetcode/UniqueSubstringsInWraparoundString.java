package org.wshuai.leetcode;

/**
 * Created by Wei on 11/5/2019.
 * #467 https://leetcode.com/problems/unique-substrings-in-wraparound-string/
 */
public class UniqueSubstringsInWraparoundString {
	public int findSubstringInWraproundString(String p) {
		int[] count = new int[26];

		int maxLengthCurr = 0;

		for(int i = 0; i < p.length(); i++){
			if(i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || p.charAt(i - 1) - p.charAt(i) == 25)){
				maxLengthCurr++;
			}else{
				maxLengthCurr = 1;
			}

			int index = p.charAt(i) - 'a';
			count[index] = Math.max(count[index], maxLengthCurr);
		}

		int sum = 0;
		for(int i = 0; i < 26; i++){
			sum += count[i];
		}
		return sum;
	}
}
