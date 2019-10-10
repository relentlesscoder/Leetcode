package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 9/25/19.
 * #1190 https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {
	public String reverseParentheses(String s) {
		int i = 0;
		int d = 0;
		StringBuilder sb = new StringBuilder();
		while(i < s.length()){
			if(s.charAt(i) == '('){
				d++;
				int left = i;
				int j = i+1;
				while(j < s.length()){
					if(s.charAt(j) == '('){
						d++;
					}else if(s.charAt(j) == ')'){
						d--;
						if(d == 0){
							StringBuilder curr = new StringBuilder(reverseParentheses(s.substring(left + 1, j)));
							sb.append(curr.reverse().toString());
							break;
						}
					}
					j++;
				}
				i = j + 1;
			}else{
				sb.append("" + s.charAt(i++));
			}
		}
		return sb.toString();
	}
}
