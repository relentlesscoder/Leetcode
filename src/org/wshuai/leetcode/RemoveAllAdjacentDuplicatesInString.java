package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 08/10/2019.
 * #1047 https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 */
public class RemoveAllAdjacentDuplicatesInString {

	// time O(n), space O(n)
	public String removeDuplicates(String S) {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < S.length(); i++){
			char c = S.charAt(i);
			if(!stack.isEmpty() && stack.peek() == c){
				stack.pop();
			}else{
				stack.push(c);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(char c : stack){
			sb.append(c);
		}
		return sb.toString();
	}
}
