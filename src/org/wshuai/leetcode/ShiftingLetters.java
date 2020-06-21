package org.wshuai.leetcode;

/**
 * Created by Wei on 11/06/2019.
 * #0848 https://leetcode.com/problems/shifting-letters/
 */
public class ShiftingLetters {

	// time O(n), space O(n)
	public String shiftingLetters(String S, int[] shifts) {
		int n = S.length(), sum = 0;
		char[] res = new char[n];
		// shift number at each index is the running sum
		// staring from the end
		for(int i = n - 1; i >= 0; i--){
			char c = S.charAt(i);
			sum = (sum + shifts[i] % 26) % 26;
			res[i] = (char)('a' + (c - 'a' + sum) % 26);
		}
		return new String(res);
	}
}
