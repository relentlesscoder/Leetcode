package org.wshuai.leetcode;

/**
 * Created by Wei on 10/23/2016.
 * #0415 https://leetcode.com/problems/add-strings/
 */
public class AddStrings {

	// time O(n)
	public String addStrings(String num1, String num2) {
		StringBuilder sb = new StringBuilder();
		for(int i = num1.length() - 1, j = num2.length() - 1, carry = 0; i >= 0 || j >= 0 || carry > 0; i--, j--){
			int a = i >= 0 ? num1.charAt(i) - '0' : 0;
			int b = j >= 0 ? num2.charAt(j) - '0' : 0;
			int sum = a + b + carry;
			sb.append(sum % 10);
			carry = sum / 10;
		}
		return sb.reverse().toString();
	}
}
