package org.wshuai.leetcode;

/**
 * Created by Wei on 10/14/2019.
 * #1121 https://leetcode.com/problems/split-a-string-in-balanced-strings/
 */
public class SplitAStringInBalancedStrings {
	public int balancedStringSplit(String s) {
		int res = 0;
		int i = 0;
		int flag = 0;
		while(i < s.length()){
			if(s.charAt(i) == 'L'){
				flag++;
			}else{
				flag--;
			}
			if(flag == 0){
				res++;
			}
			i++;
		}
		return res;
	}
}
