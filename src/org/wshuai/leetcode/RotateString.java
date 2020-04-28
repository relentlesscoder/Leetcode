package org.wshuai.leetcode;

/**
 * Created by Wei on 08/31/2019.
 * #0796 https://leetcode.com/problems/rotate-string/
 */
public class RotateString {
	// time O(n)
	public boolean rotateString(String A, String B) {
		return A.length() == B.length() && (A + A).indexOf(B) != -1;
	}
}
