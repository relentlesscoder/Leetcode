package org.wshuai.leetcode;

/**
 * Created by Wei on 11/6/19.
 * #848 https://leetcode.com/problems/shifting-letters/
 */
public class ShiftingLetters {
	public String shiftingLetters(String S, int[] shifts) {
		int N = shifts.length;
		for(int i = N - 2; i >= 0; i--){
			shifts[i] = (shifts[i] % 26 + shifts[i + 1]% 26) % 26;
		}
		StringBuilder sb = new StringBuilder();
		char[] arr = S.toCharArray();
		for(int i = 0; i < N; i++){
			int x = arr[i] - 'a';
			int f = (x + shifts[i]) % 26;
			sb.append((char)(f + 'a'));
		}
		return sb.toString();
	}
}
