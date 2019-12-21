package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/20/2019.
 * #828 https://leetcode.com/problems/unique-letter-string/
 */
public class UniqueLetterString {
	// https://leetcode.com/problems/unique-letter-string/discuss/128952/One-pass-O(N)-Straight-Forward
	public int uniqueLetterString(String S) {
		int[][] index = new int[26][2];
		for(int i = 0; i < 26; i++){
			Arrays.fill(index[i], -1);
		}
		int res = 0;
		int N = S.length();
		int mod = 1_000_000_007;
		for(int i = 0; i < N; i++){
			int c = S.charAt(i) - 'A';
			res = (res + (i - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
			index[c] = new int[]{index[c][1], i};
		}
		for(int c = 0; c < 26; c++){
			res = (res + (N - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
		}
		return res;
	}
}
