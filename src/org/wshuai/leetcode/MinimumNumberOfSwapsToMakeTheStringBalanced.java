package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/20/2023.
 * #1963 https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
 */
public class MinimumNumberOfSwapsToMakeTheStringBalanced {

    // time O(n), space O(1)
	public int minSwapsGreedy(String s) {
		int res = 0, balance = 0, n = s.length();
		char[] arr = s.toCharArray();
		for (int i = 0, j = n - 1; i < j; i++) {
			balance += arr[i] == '[' ? 1 : -1;
			// For each unclosed ']', greedily find '[' from right
			// to swap with it. Since ']' closer to the right close
			// more unclosed '[' and '[' closer to the left close
			// more unclosed ']', this greedy approach is optimal.
			if (balance == -1) {
				while (arr[j] == ']') {
					j--;
				}
				j--;
				res++;
				balance = 1;
			}
		}
		return res;
	}

	// time O(n), space O(1)
	public int minSwapsStackSpaceOptimized(String s) {
		int n = s.length(), unbalanced = 0, stackSize = 0;
		char[] arr = s.toCharArray();
		for (int i = 0; i < n; i++) {
			if (arr[i] == '[') {
				stackSize++;
			} else if (stackSize > 0) {
				stackSize--;
			} else {
				unbalanced++;
			}
		}
		return (unbalanced + 1) / 2; // + 1 to handle odd unbalanced pairs
	}

	// time O(n), space O(n)
	public int minSwapsStack(String s) {
		int n = s.length(), unbalanced = 0;
		char[] arr = s.toCharArray();
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			if (arr[i] == '[') {
				stack.push(i);
			} else if (!stack.isEmpty()) {
				stack.pop();
			} else {
				unbalanced++;
			}
		}
		return (unbalanced + 1) / 2;
	}
}
