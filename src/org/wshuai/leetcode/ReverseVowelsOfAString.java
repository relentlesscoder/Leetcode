package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 9/19/2016.
 */
public class ReverseVowelsOfAString {
	public String reverseVowels(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}
		char[] arr = s.toCharArray();
		int len = arr.length;
		Queue<Character> lst = new LinkedList<Character>();
		for (int i = 0; i < len; i++) {
			char x = arr[i];
			if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u' || x == 'A' || x == 'E' || x == 'I' || x == 'O' || x == 'U') {
				lst.offer(x);
			}
		}
		if (lst.isEmpty()) {
			return s;
		}
		for (int i = len - 1; i >= 0; i--) {
			char x = arr[i];
			if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u' || x == 'A' || x == 'E' || x == 'I' || x == 'O' || x == 'U') {
				arr[i] = lst.poll();
			}
		}
		return new String(arr);
	}
}
