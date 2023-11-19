package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2016.
 * #0067 https://leetcode.com/problems/add-binary/
 */
public class AddBinary {

	// time O(d)
	public String addBinary(String a, String b) {
		StringBuilder res = new StringBuilder();
		for(int i = a.length() - 1, j = b.length() - 1, carry = 0; i >= 0 || j >= 0 || carry > 0; i--, j--){
			int m = i >= 0 ? a.charAt(i) - '0' : 0;
			int n = j >= 0 ? b.charAt(j) - '0' : 0;
			int sum = m + n + carry;
			res.append((char)(sum % 2 + '0'));
			carry = sum >= 2 ? 1 : 0;
		}
		return res.reverse().toString();
	}
}
