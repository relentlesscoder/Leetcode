package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 9/17/19.
 * #735 https://leetcode.com/problems/asteroid-collision/
 */
public class AsteroidCollision {
	public int[] asteroidCollision(int[] asteroids) {
		if (asteroids.length == 0) {
			return new int[0];
		}
		LinkedList<Integer> stack = new LinkedList<>();
		stack.offerLast(asteroids[0]);
		int i = 1;
		while (i < asteroids.length) {
			// need to consider following cases:
			// stack 10, 3, 2 | next -5
			// stack      3, 2 | next -5
			// stack  5, 3, 2 | next -5
			// stack -5, 3, 2 | next -5
			if (asteroids[i] < 0 && !stack.isEmpty() && stack.peekLast() > 0) {
				while (!stack.isEmpty() && stack.peekLast() > 0 && Math.abs(stack.peekLast()) < Math.abs(asteroids[i])) {
					stack.pollLast();
				}
				if (stack.isEmpty() || stack.peekLast() < 0) {
					stack.offerLast(asteroids[i]);
				} else if (Math.abs(stack.peekLast()) == Math.abs(asteroids[i])) {
					stack.pollLast();
				}
			} else {
				stack.offerLast(asteroids[i]);
			}
			i++;
		}
		int[] res = new int[stack.size()];
		int j = 0;
		while (!stack.isEmpty()) {
			res[j++] = stack.pollFirst();
		}
		return res;
	}
}
