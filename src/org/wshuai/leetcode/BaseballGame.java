package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 8/22/19.
 * #682 https://leetcode.com/problems/baseball-game/
 */
public class BaseballGame {
	public int calPoints(String[] ops) {
		LinkedList<Integer> queue = new LinkedList<>();
		for (String op : ops) {
			if (op.equals("+")) {
				queue.push(queue.get(0) + queue.get(1));
			} else if (op.equals("D")) {
				queue.push(2 * queue.get(0));
			} else if (op.equals("C")) {
				queue.pop();
			} else {
				int score = Integer.parseInt(op);
				queue.push(score);
			}
		}
		int sum = 0;
		while (!queue.isEmpty()) {
			sum += queue.pop();
		}
		return sum;
	}
}
