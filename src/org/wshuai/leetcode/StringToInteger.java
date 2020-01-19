package org.wshuai.leetcode;

/**
 * Created by Wei on 08/13/2016.
 * #0008 https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToInteger {
	public int myAtoi(String str) {
		long res = 0;
		int i = 0;
		while(i < str.length() && str.charAt(i) == ' '){
			i++;
		}
		if(i == str.length()){
			return 0;
		}
		long num = 0;
		char c = str.charAt(i);
		boolean neg = false;
		if(c == '+' || c == '-'){
			i++;
			if(c == '-'){
				neg = true;
			}
		}
		while(i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9'){
			res *= 10;
			int d = str.charAt(i) - '0';
			res += neg ? -d : d;
			if(res > Integer.MAX_VALUE){
				return Integer.MAX_VALUE;
			}
			if(res < Integer.MIN_VALUE){
				return Integer.MIN_VALUE;
			}
			i++;
		}
		return (int)res;
	}
}
