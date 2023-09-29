package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/28/2023.
 * #1823 https://leetcode.com/problems/find-the-winner-of-the-circular-game/
 */
public class FindTheWinnerOfTheCircularGame {

	// time O(n), space O(1)
	public int findTheWinnerIterative(int n, int k) {
		int res = 0;
		for (int i = 2; i <= n; i++) {
			res = (res + k) % i;
		}
		return res + 1;
	}

	// time O(n), space O(n)
	public int findTheWinnerRecursive(int n, int k) {
		return findTheWinnnerZeroBased(n, k) + 1; //https://cs.stackexchange.com/questions/7048/a-recursive-formula-for-generalized-josephus-problem
	}

	private int findTheWinnnerZeroBased(int n, int k) {
		if (n == 1) {
			return 0;
		}
		return (findTheWinnnerZeroBased(n - 1, k) + k) % n;
	}

	// time O(n * k), space O(n)
	public int findTheWinner(int n, int k) {
		Deque<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i<= n; i++) {
			queue.offerLast(i);
		}
		while (queue.size() > 1) {
			int c = k;
			while (c-- > 0) {
				queue.offerLast(queue.pollFirst());
			}
			queue.pollLast();
		}
		return queue.peekFirst();
	}
}
