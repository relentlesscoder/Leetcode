package org.wshuai.leetcode;

/**
 * Created by Wei on 10/23/2016.
 * #0415 https://leetcode.com/problems/add-strings/
 */
public class AddStrings {
	// time O(n)
	public String addStrings(String num1, String num2) {
		StringBuilder sb = new StringBuilder();
		char[] rs = new StringBuilder(num1).reverse().toString().toCharArray();
		char[] rt = new StringBuilder(num2).reverse().toString().toCharArray();
		int i = 0, j = 0, c = 0;
		while (i < rs.length || j < rt.length || c > 0) {
			int v1 = i < rs.length ? rs[i++] - '0' : 0;
			int v2 = j < rt.length ? rt[j++] - '0' : 0;
			int sum = v1 + v2 + c;
			sb.append(sum % 10);
			c = sum / 10;
		}
		return sb.reverse().toString();
	}
}
