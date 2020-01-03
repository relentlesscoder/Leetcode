package org.wshuai.leetcode;

/**
 * Created by Wei on 12/23/19.
 * #940 https://leetcode.com/problems/distinct-subsequences-ii/
 */
public class DistinctSubsequencesII {
	public int distinctSubseqII(String S) {
		/*
		Input: "aba"
		Current parsed: "ab"

		ends with 'a': ["a"]
		ends with 'b': ["ab","b"]

		"a" -> "aa"
		"ab" -> "aba"
		"b" -> "ba"
		"" -> "a"

		ends with 'a': ["aa","aba","ba","a"]
		ends with 'b': ["ab","b"]
		result: 6
		 */
		int endWith[] = new int[26];
		int res = 0, added = 0, mod = 1_000_000_007;
		for(char c : S.toCharArray()){
			added = (res + 1 - endWith[c - 'a']) % mod;
			res = (res + added) % mod;
			endWith[c - 'a'] = (endWith[c - 'a'] + added) % mod;
		}
		return (res + mod) % mod;
	}
}
