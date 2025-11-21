package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/10/2019.
 * #1047 https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 */
public class RemoveAllAdjacentDuplicatesInString {

    // time O(n), space O(n)
    public String removeDuplicates(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && s.charAt(stack.peek()) == c) {
                stack.pop();
            } else {
                stack.push(i);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(s.charAt(stack.pollLast()));
        }
        return res.toString();
    }
}
