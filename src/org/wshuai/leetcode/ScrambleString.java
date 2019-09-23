package org.wshuai.leetcode;

/**
 * Created by Wei on 2/5/17.
 * #87 https://leetcode.com/problems/scramble-string/
 */
public class ScrambleString {

	//DP
	public boolean isScramble(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}
		if (s1.equals(s2)) {
			return true;
		}
		int len1 = s1.length();
		int len2 = s2.length();
		if (len1 != len2) {
			return false;
		}
		boolean[][][] aux = new boolean[len1][len1][len1 + 1];
		for (int len = 1; len <= len1; len++) {
			for (int i = 0; i <= len1 - len; i++) {
				for (int j = 0; j <= len1 - len; j++) {
					if (len == 1) {
						aux[i][j][len] = (s1.charAt(i) == s2.charAt(j));
						continue;
					}

					aux[i][j][len] = false;
					for (int cLen = 1; cLen < len; cLen++) {
						if ((aux[i][j][cLen] && aux[i + cLen][j + cLen][len - cLen])
								|| (aux[i][j + len - cLen][cLen] && aux[i + cLen][j][len - cLen])) {
							aux[i][j][len] = true;
							break;
						}
					}
				}
			}
		}

		return aux[0][0][len1];
	}

	//TLE
	public boolean isScrambleRecursive(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}
		if (s1.equals(s2)) {
			return true;
		}
		int len1 = s1.length();
		int len2 = s2.length();
		if (len1 != len2) {
			return false;
		}
		if (len1 == 1) {
			return s1.equals(s2);
		}
		for (int i = 1; i < len1; i++) {
			if (isScrambleRecursive(s1.substring(0, i), s2.substring(0, i))
					&& isScrambleRecursive(s1.substring(i), s2.substring(i))) {
				return true;
			}

			if (isScrambleRecursive(s1.substring(0, i), s2.substring(len1 - i))
					&& isScrambleRecursive(s1.substring(i), s2.substring(0, len1 - i))) {
				return true;
			}
		}
		return false;
	}
}
