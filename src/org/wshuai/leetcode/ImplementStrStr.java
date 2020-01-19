package org.wshuai.leetcode;

/**
 * Created by Wei on 08/16/2016.
 * #0028 https://leetcode.com/problems/implement-strstr/
 */
public class ImplementStrStr {

	// time O(m + n), space O(n), 2ms
	public int strStrKMP(String haystack, String needle) {
		if(needle.length() == 0){
			return 0;
		}
		if(haystack.length() < needle.length()){
			return -1;
		}
		return kmp(haystack.toCharArray(), needle.toCharArray());
	}

	private int kmp(char[] text, char[] pattern){
		int[] lsp = constructLsp(pattern);
		int j = 0;
		for(int i = 0; i < text.length; i++){
			while(j > 0 && text[i] != pattern[j]){
				j = lsp[j - 1];
			}
			if(text[i] == pattern[j]){
				j++;
				if(j == pattern.length){
					return i - j + 1;
				}
			}
		}
		return -1;
	}

	private int[] constructLsp(char[] pattern){
		int[] lsp = new int[pattern.length];
		lsp[0] = 0;
		for(int i = 1; i < pattern.length; i++){
			int j = lsp[i - 1];
			while(j > 0 && pattern[i] != pattern[j]){
				// consider the case when pattern is AAACAAAA
				// when i = 7, the LSP is
				// 0,1,2,0,1,2,3
				// since S[i] != S[LSP[i - 1]] (A != C), we can extend
				// the previous matching prefix suffix
				// LSP[6] = 3 -> S[0-2] == S[4-6] -> S[1-2] == S[5,6]
				// LSP[3 - 1] = 2 -> S[0-1] == S[1-2]
				// -> S[5,6] == S[0-1]
				// if S[2] == S[7], we can extend this matching prefix
				// postfix to 3
				j = lsp[j - 1];
			}
			if(pattern[i] == pattern[j]){
				j++;
			}
			lsp[i] = j;
		}
		return lsp;
	}

	private static final int p = 101;
	private static final int s = 256;

	// time O(m*n) 1ms
	public int strStrRabinKarp(String haystack, String needle) {
		int m = haystack.length();
		int n = needle.length();
		if(n == 0){
			return 0;
		}
		if(m == 0 || m < n){
			return -1;
		}
		char[] text = haystack.toCharArray();
		char[] pattern = needle.toCharArray();
		int pow = 1;
		for(int i = 0; i < n - 1; i++){
			pow = (pow * s) % p;
		}
		int patternHash = 0;
		int textHash = 0;
		for(int i = 0; i < n; i++){
			patternHash = (patternHash * s + pattern[i]) % p;
			textHash = (textHash * s + text[i]) % p;
		}

		if(patternHash == textHash && matchString(pattern, text, 0)){
			return 0;
		}
		for(int i = 0; i <= m - n; i++){
			if(patternHash == textHash && matchString(pattern, text, i)){
				return i;
			}
			if(i < m - n){
				textHash = (s * (textHash - text[i] * pow) + text[i + n]) % p;
				textHash += textHash < 0 ? p : 0;
			}
		}
		return -1;
	}

	private boolean matchString(char[] s, char[] t, int j){
		int i = 0;
		while(i < s.length){
			if(s[i++] != t[j++]){
				return false;
			}
		}
		return true;
	}
}
