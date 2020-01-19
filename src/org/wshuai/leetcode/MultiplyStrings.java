package org.wshuai.leetcode;

/**
 * Created by Wei on 10/05/2016.
 * #0043 https://leetcode.com/problems/multiply-strings/
 */
public class MultiplyStrings {
	// O(m * n)
	public String multiply(String num1, String num2) {
		if(num1.equals("0") || num2.equals("0")){
			return "0";
		}
		int n1 = num1.length();
		int n2 = num2.length();
		int[] res = new int[n1 + n2 - 1];
		char[] arr1 = new StringBuilder(num1).reverse().toString().toCharArray();
		char[] arr2 = new StringBuilder(num2).reverse().toString().toCharArray();
		for(int i = 0; i < arr1.length; i++){
			for(int j = 0; j < arr2.length; j++){
				res[i + j] += (arr1[i] - '0') * (arr2[j] - '0');
			}
		}
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < res.length; i++){
			int sum = res[i] + carry;
			res[i] = sum % 10;
			carry = sum / 10;
			sb.append(res[i]);
		}
		sb.append(carry > 0 ? "" + carry : "");
		return sb.reverse().toString();
	}
}
