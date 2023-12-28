package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by Wei on 12/28/2023.
 * #1717 https://leetcode.com/problems/maximum-score-from-removing-substrings/
 */
public class MaximumScoreFromRemovingSubstrings {

    // time O(n), space O(n)
    public int maximumGain(String s, int x, int y) {
        int res = 0;
        char a = 'a', b = 'b';
        if (y > x) {
            char temp = a;
            a = b;
            b = temp;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == b && !queue.isEmpty() && s.charAt(queue.peekLast()) == a) {
                res += Math.max(x, y);
                queue.pollLast();
            } else {
                queue.offerLast(i);
            }
        }
        char temp = a;
        a = b;
        b = temp;
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            int index = queue.pollFirst();
            char c = s.charAt(index);
            if (c == b && !stack.isEmpty() && s.charAt(stack.peek()) == a) {
                res += Math.min(x, y);
                stack.pop();
            } else {
                stack.push(index);
            }
        }
        return res;
    }
}
