package org.wshuai.leetcode;

/**
 * Created by Wei on 08/30/2020.
 * #1556 https://leetcode.com/problems/thousand-separator/
 */
public class ThousandSeparator {

	// time O(n), space O(n)
	public String thousandSeparator(int n) {
		char[] arr = Integer.toString(n).toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i = arr.length - 1, j = 0; i >= 0; i--, j++){
			if(j > 0 && j % 3 == 0){
				sb.append(".");
			}
			sb.append(arr[i]);
		}
		return sb.reverse().toString();
	}
}
