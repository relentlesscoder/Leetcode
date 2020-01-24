package org.wshuai.leetcode;

/**
 * Created by Wei on 06/27/2017.
 * #0214 https://leetcode.com/problems/shortest-palindrome/
 */
public class ShortestPalindrome {
	// time O(n)
	public String shortestPalindromeKMP(String s) {
		// wants to know the longest palindrome substring starts from index 0
		// since the property of palindrome is: pal = reverse(pal)
		// we can use the trick below to construct a new string and
		// calculate it's longest common prefix suffix (KMP)
		String temp = s + "#" + new StringBuilder(s).reverse().toString();
		int[] lsp = buildLSP(temp);
		return new StringBuilder(s.substring(lsp[lsp.length - 1])).reverse().toString() + s;
	}

	private int[] buildLSP(String s){
		int[] lsp = new int[s.length()];
		lsp[0] = 0;
		int j = 0;
		for(int i = 1; i < s.length(); i++){
			while(j > 0 && s.charAt(i) != s.charAt(j)){
				j = lsp[j - 1];
			}
			if(s.charAt(i) == s.charAt(j)){
				j++;
			}
			lsp[i] = j;
		}
		return lsp;
	}

	// time O(n^2)
	public String shortestPalindrome(String s) {
		if(s == null || s.length() == 0){
			return "";
		}
		char[] arr = s.toCharArray();
		int n = s.length(), mid = (n - 1) / 2;
		for(int i = mid; i >= 0; i--){
			int left = i, right = i;
			while(left >= 0 && arr[left--] == arr[right++]){
				if(left < 0){
					return new StringBuilder(s.substring(right)).reverse().toString() + s;
				}
			}
			left = i - 1;
			right = i;
			while(left >= 0 && arr[left--] == arr[right++]){
				if(left < 0){
					return new StringBuilder(s.substring(right)).reverse().toString() + s;
				}
			}
		}
		return "";
	}
}
