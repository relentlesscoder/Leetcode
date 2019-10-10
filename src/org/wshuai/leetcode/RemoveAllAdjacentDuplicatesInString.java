package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 8/10/19.
 * #1047 https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 */
public class RemoveAllAdjacentDuplicatesInString {
	public String removeDuplicates(String S) {
		Stack<Character> stack = new Stack<Character>();
		char[] arr = S.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (stack.isEmpty() || stack.peek() != arr[i]) {
				stack.push(arr[i]);
			} else {
				while (!stack.isEmpty() && stack.peek() == arr[i]) {
					stack.pop();
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append("" + stack.pop());
		}
		return sb.reverse().toString();
	}
}
