package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/16.
 * #1256 https://leetcode.com/problems/encode-number/
 */
public class EncodeNumber {
	// WTF
	public String encode(int num) {
		return Integer.toBinaryString(num + 1).substring(1);
	}
}
