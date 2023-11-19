package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2023.
 * #2075 https://leetcode.com/problems/decode-the-slanted-ciphertext/
 */
public class DecodeTheSlantedCiphertext {

	// time O(n), space O(n)
	public String decodeCiphertext(String encodedText, int rows) {
		StringBuilder res = new StringBuilder();
		int n = encodedText.length(), cols = n / rows;
		for (int k = 0; k < cols; k++) { // diagonal (anti-diagonal) traversal
			for (int i = 0, j = k; i < rows && j < cols; i++, j++) {
				res.append(encodedText.charAt(i * cols + j));
			}
		}
		int i = res.length() - 1;
		for (; i >= 0 && res.charAt(i) == ' '; i--) ; // trim ending white spaces
		return res.substring(0, i + 1);
	}
}
