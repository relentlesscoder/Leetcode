package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/12/2019.
 * #0678 https://leetcode.com/problems/valid-parenthesis-string/
 */
public class ValidParenthesisString {

    // time O(n), space O(1)
    public boolean checkValidString(String s) {
        // https://leetcode.com/problems/valid-parenthesis-string/discuss/107577/Short-Java-O(n)-time-O(1)-space-one-pass
        int low = 0, high = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                if (--high < 0) {
                    return false;
                }
            } else {
                low--;
                high++;
            }
            low = Math.max(low, 0);
        }
        return low == 0;
    }

    // time O(n), space O(1)
    public boolean checkValidStringTwoPass(String s) {
        // #2116 https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/
        int n = s.length(), balance = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ')') {
                balance--;
            } else {
                balance++;
            }
            if (balance < 0) {
                return false;
            }
        }
        balance = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                balance--;
            } else {
                balance++;
            }
            if (balance < 0) {
                return false;
            }
        }
        return true;
    }

    // time O(n), space O(n)
    public boolean checkValidStringStack(String s) {
        int n = s.length();
        Deque<Integer> opens = new ArrayDeque<>();
        Deque<Integer> asts = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (!opens.isEmpty()) {
                    opens.pop();
                } else if (!asts.isEmpty()) {
                    asts.pop();
                } else {
                    return false;
                }
            } else if (c == '(') {
                opens.push(i);
            } else {
                asts.push(i);
            }
        }
        while (!opens.isEmpty() && !asts.isEmpty() && asts.peek() > opens.peek()) {
            asts.pop();
            opens.pop();
        }
        return opens.isEmpty();
    }
}
