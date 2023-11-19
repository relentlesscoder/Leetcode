package org.wshuai.leetcode;

/**
 * Created by Wei on 09/29/2023.
 * #2609 https://leetcode.com/problems/find-the-longest-balanced-substring-of-a-binary-string/
 */
public class FindTheLongestBalancedSubstringOfABinaryString {

	// time O(n), space O(1)
	public int findTheLongestBalancedSubstringCounting(String s) {
		int res = 0, oneCount = 0, zeroCount = 0;
		for (char c : s.toCharArray()) {
			if (c == '0') {
				zeroCount = (oneCount > 0 ? 1 : zeroCount + 1); // reset zero count if previous character is '1'
				oneCount = 0; // reset one count to 0 since we saw a '0'
			} else {
				oneCount++;
			}
			if (zeroCount >= oneCount) { // if we have enough leading zeros then we can build balanced substring
				res = Math.max(res, oneCount);
			}
		}
		return (res << 1);
	}

	// time O(n), space O(1)
	public int findTheLongestBalancedSubstring(String s) {
		int res = 0, n = s.length();
		for (int i = 1; i < n; i++) {
			if (s.charAt(i) == '1' && s.charAt(i - 1) == '0') { // when we find a center pair "01", we can expand to both side simultaneously to get the longest balanced substring
				int len = 2, j = i + 1, k = i - 2;
				while (k >= 0 && j < n && s.charAt(j) == '1' && s.charAt(k) == '0') {
					len += 2;
					k--;
					j++;
				}
				i = j; // advance to the end of the current balanced string since it is impossible for it to be part of next balanced string
				res = Math.max(res, len);
			}
		}
		return res;
	}
}
