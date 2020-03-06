package org.wshuai.leetcode;

/**
 * Created by Wei on 04/04/2017.
 * #0521 https://leetcode.com/problems/longest-uncommon-subsequence-i/
 */
public class LongestUncommonSubsequenceI {
	// time O(n)
	public int findLUSlength(String a, String b) {
		return a.equals(b) ? -1 : Math.max(a.length(), b.length());
	}
}
