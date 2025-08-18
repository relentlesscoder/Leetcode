package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/17/2025.
 * #3561 https://leetcode.com/problems/resulting-string-after-adjacent-removals/
 */
public class ResultingStringAfterAdjacentRemovals {

    private static final char[] map = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    // time O(n), space O(n)
    public String resultingString(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (!stack.isEmpty() && (stack.peek() == map[(index + 1) % 26]
                    || stack.peek() == map[(index + 25) % 26])) { // (index - 1 + 26) % 26
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pollLast());
        }
        return res.toString();
    }
}
