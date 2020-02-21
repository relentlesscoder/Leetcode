package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/13/2019.
 * #0402 https://leetcode.com/problems/remove-k-digits/
 */
public class RemoveKDigits {
	// time O(n)
	// https://leetcode.com/problems/remove-k-digits/discuss/88708/Straightforward-Java-Solution-Using-Stack
	public String removeKdigits(String num, int k) {
		int len = num.length();
		// corner case
		if (k == len)
			return "0";

		Stack<Character> stack = new Stack<>();
		int i = 0;
		while (i < num.length()) {
			// whenever meet a digit which is less than the previous digit, discard the previous one
			while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
				stack.pop();
				k--;
			}
			stack.push(num.charAt(i));
			i++;
		}

		// corner case like "1111"
		while (k-- > 0) {
			stack.pop();
		}

		// construct the number from the stack
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		sb.reverse();

		//remove all the 0 at the head
		while (sb.length() > 1 && sb.charAt(0) == '0'){
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
}
