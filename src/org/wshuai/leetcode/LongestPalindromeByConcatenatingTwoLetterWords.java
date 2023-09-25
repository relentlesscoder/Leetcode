package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2023.
 * #2855 https://leetcode.com/problems/minimum-right-shifts-to-sort-the-array/
 */
public class LongestPalindromeByConcatenatingTwoLetterWords {

	// time O(n), space O(1)
	public int longestPalindrome(String[] words) {
		int res = 0, upper = 26 * 26;
		int[] wordCount = new int[upper];
		for (String word : words) {
			int key = 26 * (word.charAt(0) - 'a') + (word.charAt(1) - 'a'); // encode the string to 26 * c1 + c2, think about the index of a 26 x 26 matrix
			if (word.charAt(0) == word.charAt(1)) { // if the word has two same characters, we check if we can find itself to form a pair
				if (wordCount[key] > 0) {
					res += 4;
					wordCount[key]--;
				} else {
					wordCount[key] = 1;
				}
			} else {
				int reverseKey = 26 * (word.charAt(1) - 'a') + (word.charAt(0) - 'a'); // if the word has two different characters, we check if we can find its reverse to find a pair
				if (wordCount[reverseKey] > 0) {
					res += 4;
					wordCount[reverseKey]--;
				} else {
					wordCount[key]++;
				}
			}
		}
		for (int i = 0; i < upper; i++) { // if we still can find one (or more) word that has two same characters, we can only use 1 of them as the central of the palindrome
			if (wordCount[i] > 0 && i % 26 == i / 26) {
				res += 2;
				break;
			}
		}
		return res;
	}
}
