package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/22/2019.
 * #0682 https://leetcode.com/problems/baseball-game/
 */
public class BaseballGame {

    // time O(n), space O(n)
    public int calPoints(String[] operations) {
        int sum = 0, val = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String op : operations) {
            if (op.equals("+")) {
                int top = stack.pop();
                val = top + stack.peek();
                stack.push(top);
                stack.push(val);
                sum += val;
            } else if (op.equals("D")) {
                val = stack.peek() * 2;
                stack.push(val);
                sum += val;
            } else if (op.equals("C")) {
                sum -= stack.pop();
            } else {
                val = Integer.parseInt(op);
                stack.push(val);
                sum += val;
            }
        }
        return sum;
    }
}
