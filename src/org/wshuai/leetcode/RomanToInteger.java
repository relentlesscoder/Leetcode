package org.wshuai.leetcode;

/**
 * Created by Wei on 08/13/2016.
 * #0013 https://leetcode.com/problems/roman-to-integer/
 */
public class RomanToInteger {
	public int romanToInt(String s) {
		int res = 0;
		int i = 0;
		int n = s.length();
		while(i < n){
			char cur = s.charAt(i);
			if(cur == 'I'){
				if(i != n - 1 && s.charAt(i +1) == 'V'){
					res += 4;
					i++;
				}else if(i != n - 1 && s.charAt(i + 1) == 'X'){
					res += 9;
					i++;
				}else{
					res++;
				}
			}
			if(cur == 'X'){
				if(i != n - 1 && s.charAt(i +1) == 'L'){
					res += 40;
					i++;
				}else if(i != n - 1 && s.charAt(i + 1) == 'C'){
					res += 90;
					i++;
				}else{
					res += 10;
				}
			}
			if(cur == 'C'){
				if(i != n - 1 && s.charAt(i +1) == 'D'){
					res += 400;
					i++;
				}else if(i != n - 1 && s.charAt(i + 1) == 'M'){
					res += 900;
					i++;
				}else{
					res += 100;
				}
			}
			if(cur == 'M'){
				res += 1000;
			}
			if(cur == 'V'){
				res += 5;
			}
			if(cur == 'L'){
				res += 50;
			}
			if(cur == 'D'){
				res += 500;
			}
			i++;
		}
		return res;
	}
}
