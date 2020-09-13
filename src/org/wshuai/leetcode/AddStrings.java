package org.wshuai.leetcode;

/**
 * Created by Wei on 10/23/2016.
 * #0415 https://leetcode.com/problems/add-strings/
 */
public class AddStrings {

	// time O(n)
	public String addStrings(String num1, String num2) {
		StringBuilder sb = new StringBuilder();
		int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
		while(i >= 0 || j >= 0 || carry > 0){
			int d1 = i >= 0 ? (num1.charAt(i--) - '0') : 0;
			int d2 = j >= 0 ? (num2.charAt(j--) - '0') : 0;
			int sum = d1 + d2 + carry;
			sb.append(sum % 10);
			carry = sum / 10;
		}
		return sb.reverse().toString();
	}
}
