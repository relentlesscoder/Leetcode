package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Wei on 9/14/19.
 * #975 https://leetcode.com/problems/odd-even-jump/
 */
public class OddEvenJump {

	// see https://zhuanlan.zhihu.com/p/55145888
	public int oddEvenJumps(int[] A) {
		int n = A.length;
		Stack<Integer> stack = new Stack<>();

		Integer[] indexs1 = new Integer[n];
		Integer[] indexs2 = new Integer[n];
		for (int i = 0; i < n; i++) {
			indexs1[i] = i;
			indexs2[i] = i;
		}

		// A: [10, 13, 12, 14, 15]
		// indexs1: [0, 2, 1, 3, 4]
		// oddIndex: [2, 3, 3, 4, 0]
		// after sorting the index array, the value of the index after is
		// always greater or equal than that of the one before
		// so use stack of minimums to find the next index with greater or equal value
		Arrays.sort(indexs1, (i, j) -> (A[i] - A[j]));
		int[] oddIndex = new int[n];
		Arrays.fill(oddIndex, -1);
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && stack.peek() < indexs1[i]) {
				oddIndex[stack.pop()] = indexs1[i];
			}
			stack.push(indexs1[i]);
		}

		Arrays.sort(indexs2, (i, j) -> (A[j] - A[i]));
		stack.clear();
		int[] evenIndex = new int[n];
		Arrays.fill(evenIndex, -1);
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && stack.peek() < indexs2[i]) {
				evenIndex[stack.pop()] = indexs2[i];
			}
			stack.push(indexs2[i]);
		}

		int ans = 1;
		boolean[] odd = new boolean[n];
		boolean[] even = new boolean[n];
		odd[n - 1] = even[n - 1] = true;
		for (int i = n - 2; i >= 0; i--) {
			odd[i] = oddIndex[i] == -1 ? false : even[oddIndex[i]];
			even[i] = evenIndex[i] == -1 ? false : odd[evenIndex[i]];
			if (odd[i]) {
				ans++;
			}
		}
		return ans;
	}
}
