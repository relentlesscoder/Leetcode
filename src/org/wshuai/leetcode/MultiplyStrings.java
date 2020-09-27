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
		int m = num1.length(), n = num2.length();
		char[] arr1 = new StringBuilder(num1).reverse().toString().toCharArray();
		char[] arr2 = new StringBuilder(num2).reverse().toString().toCharArray();
		int[] temp = new int[m + n - 1];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				temp[i + j] += (arr1[i] - '0') * (arr2[j] - '0');
			}
		}
		StringBuilder res = new StringBuilder();
		int carry = 0;
		for(int i = 0; i < temp.length; i++){
			int sum = carry + temp[i];
			res.append(sum % 10);
			carry = sum / 10;
		}
		res.append(carry > 0 ? String.valueOf(carry) : "");
		return res.reverse().toString();
	}
}
