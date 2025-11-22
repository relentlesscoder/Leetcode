package org.wshuai.leetcode;

/**
 * Created by Wei on 09/11/2019.
 * #1111 https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/
 */
public class MaximumNestingDepthOfTwoValidParenthesesStrings {

	// time O(n), space O(1)
	public int[] maxDepthAfterSplit(String seq) {
		int n = seq.length(), depth = 0;
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			if (seq.charAt(i) == '(') {
				depth++;
				res[i] = depth % 2;
			} else {
				res[i] = depth % 2;
				depth--;
			}
		}
		return res;
	}
}
