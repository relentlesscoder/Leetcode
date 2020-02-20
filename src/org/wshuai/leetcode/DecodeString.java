package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/29/2016.
 * #0394 https://leetcode.com/problems/decode-string/
 */
public class DecodeString {
	// time O(n)
	public String decodeString(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Stack<Integer> count = new Stack<>();
		Stack<StringBuilder> decode = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '[') {
				count.push(Integer.parseInt(sb.toString()));
				sb = new StringBuilder();
			} else if (c == ']') {
				int repeat = count.pop();
				String cur = sb.toString();
				while (repeat-- > 0) {
					decode.peek().append(cur);
				}
				sb = decode.pop();
			} else {
				if (Character.isDigit(c) && (i == 0 || !Character.isDigit(s.charAt(i - 1)))) {
					decode.push(sb);
					sb = new StringBuilder();
				}
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
