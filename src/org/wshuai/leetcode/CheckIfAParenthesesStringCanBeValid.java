package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/20/2023.
 * #2116 https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/
 */
public class CheckIfAParenthesesStringCanBeValid {

    // time O(n), space O(1)
    public boolean canBeValid(String s, String locked) {
        int n = s.length(), high = 0, low = 0;
        if ((n & 1) == 1) {
            return false;
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (locked.charAt(i) == '1') {
                if (arr[i] == ')') {
                    low--;
                    if (--high < 0) {
                        return false;
                    }
                } else {
                    low++;
                    high++;
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
    public boolean canBeValidTwoPass(String s, String locked) {
        // #0678 https://leetcode.com/problems/valid-parenthesis-string/
        int n = s.length(), balance = 0;
        if ((n & 1) == 1) {
            return false;
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (locked.charAt(i) == '1' && arr[i] == ')') {
                if (--balance < 0) {
                    return false;
                }
            } else {
                balance++;
            }
        }
        balance = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (locked.charAt(i) == '1' && arr[i] == '(') {
                if (--balance < 0) {
                    return false;
                }
            } else {
                balance++;
            }
        }
        return true;
    }

    // time O(n), space O(n)
    public boolean canBeValidStack(String s, String locked) {
        int n = s.length();
        if ((n & 1) == 1) {
            return false;
        }
        Deque<Integer> opens = new ArrayDeque<>();
        Deque<Integer> asts = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (locked.charAt(i) == '1') {
                if (c == ')') {
                    if (!opens.isEmpty()) {
                        opens.pop();
                    } else if (!asts.isEmpty()) {
                        asts.pop();
                    } else {
                        return false;
                    }
                } else {
                    opens.push(i);
                }
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
