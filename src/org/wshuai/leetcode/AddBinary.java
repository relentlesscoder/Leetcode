package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2016.
 * #0067 https://leetcode.com/problems/add-binary/
 */
public class AddBinary {
	// O(Math.max(a, b))
	public String addBinary(String a, String b) {
		char[] A = new StringBuilder(a).reverse().toString().toCharArray();
		char[] B = new StringBuilder(b).reverse().toString().toCharArray();
		int carry = 0, i = 0, j = 0;
		StringBuilder sb = new StringBuilder();
		while(i < A.length || j < B.length || carry > 0){
			int v1 = i >= A.length || A[i] == '0' ? 0 : 1;
			int v2 = j >= B.length || B[j] == '0' ? 0 : 1;
			int sum = v1 + v2 + carry;
			sb.append(sum % 2);
			carry = sum / 2;
			i++;
			j++;
		}
		return sb.reverse().toString();
	}
}
