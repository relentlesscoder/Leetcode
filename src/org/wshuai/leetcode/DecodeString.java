package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/29/2016.
 * #0394 https://leetcode.com/problems/decode-string/
 */
public class DecodeString {

	// time O(n), space O(n)
	public String decodeString(String s) {
		if(s == null || s.isEmpty()){
			return "";
		}
		int n = s.length();
		StringBuilder sb = new StringBuilder();
		Stack<Integer> count = new Stack<>();
		Stack<StringBuilder> stack = new Stack<>();
		for(int i = 0; i < n; i++){
			char c = s.charAt(i);
			if(c == '['){
				count.push(Integer.parseInt(sb.toString()));
				sb = new StringBuilder();
			}else if(c == ']'){
				String cur = sb.toString();
				int repeat = count.pop();
				while(repeat-- > 0){
					stack.peek().append(cur);
				}
				sb = stack.pop();
			}else{
				if(Character.isDigit(c) &&
					(i == 0 || !Character.isDigit(s.charAt(i - 1)))){
					stack.push(sb);
					sb = new StringBuilder();
				}
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
