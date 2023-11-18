package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/17/2023.
 * #2696 https://leetcode.com/problems/minimum-string-length-after-removing-substrings/
 */
public class MinimumStringLengthAfterRemovingSubstrings {

    // time O(n), space O(n)
    public int minLength(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c == 'B' && !stack.isEmpty() && s.charAt(stack.peek()) == 'A')
                    || (c == 'D' && !stack.isEmpty() && s.charAt(stack.peek()) == 'C')) {
                stack.pop();
            } else {
                stack.push(i);
            }
        }
        return stack.size();
    }
}
