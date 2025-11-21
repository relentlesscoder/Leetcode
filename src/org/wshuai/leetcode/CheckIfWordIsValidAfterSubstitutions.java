package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/21/2019.
 * #1003 https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/
 */
public class CheckIfWordIsValidAfterSubstitutions {

    // time O(n), space O(n)
    public boolean isValid(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            if (c1 == 'c' && stack.size() >= 2) {
                int idx = stack.pop();
                char c2 = s.charAt(idx);
                if (c2 == 'b' && s.charAt(stack.peek()) == 'a') {
                    stack.pop();
                } else {
                    stack.push(idx);
                    stack.push(i);
                }
            } else {
                stack.push(i);
            }
        }
        return stack.isEmpty();
    }
}
