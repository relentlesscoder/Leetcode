package org.wshuai.leetcode;

/**
 * Created by Wei on 08/13/2016.
 * #0008 https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToInteger {

	// time O(n)
	public int myAtoi(String str) {
		if(str == null || str.isEmpty()){
			return 0;
		}
		int i = 0, n = str.length();
		while(i < n && str.charAt(i) == ' '){
			i++;
		}
		if(i == n || (str.charAt(i) != '-' && str.charAt(i) != '+'
				&& !Character.isDigit(str.charAt(i)))){
			return 0;
		}
		boolean negative = false;
		if(str.charAt(i) == '-' || str.charAt(i) == '+'){
			negative = str.charAt(i) == '-';
			i++;
		}
		long temp = 0;
		while(i < n && Character.isDigit(str.charAt(i))){
			temp = temp * 10 + (str.charAt(i) - '0');
			if(negative && -temp < Integer.MIN_VALUE){
				return Integer.MIN_VALUE;
			}
			if(!negative && temp > Integer.MAX_VALUE){
				return Integer.MAX_VALUE;
			}
			i++;
		}
		return (int)(negative ? -temp : temp);
	}
}
