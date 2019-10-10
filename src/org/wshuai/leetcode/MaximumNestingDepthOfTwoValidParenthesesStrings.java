package org.wshuai.leetcode;

/**
 * Created by Wei on 9/11/2019.
 * #1111 https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/
 */
public class MaximumNestingDepthOfTwoValidParenthesesStrings {
	public int[] maxDepthAfterSplit(String seq) {
		int A = 0;
		int B = 0;
		int n = seq.length();
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			if (seq.charAt(i) == '(') {
				if (A < B) {
					++A;
				} else {
					++B;
					res[i] = 1;
				}
			} else {
				if (A > B) {
					--A;
				} else {
					--B;
					res[i] = 1;
				}
			}
		}
		return res;
	}
}
