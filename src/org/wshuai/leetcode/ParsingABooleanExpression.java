package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/10/2019.
 * #1106 https://leetcode.com/problems/parsing-a-boolean-expression/
 */
public class ParsingABooleanExpression {

    // time O(n), space O(n)
    public boolean parseBoolExpr(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] expr = expression.toCharArray();
        for (char c : expr) {
            if (isOperator(c) || c == 't' || c == 'f') {
                stack.push(c);
            } else if (c == ')') {
                int[] bools = new int[2];
                while (!stack.isEmpty() && !isOperator(stack.peek())) {
                    if (stack.pop() == 't') {
                        bools[0]++;
                    } else {
                        bools[1]++;
                    }
                }
                char opr = stack.pop();
                char res = evaluate(opr, bools);
                stack.push(res);
            }
        }
        return stack.peek() == 't';
    }

    private char evaluate(char opr, int[] bools) {
        if (opr == '!') {
            return bools[0] > 0 ? 'f' : 't';
        } else if (opr == '&') {
            return bools[1] > 0 ? 'f' : 't';
        }
        return bools[0] > 0 ? 't' : 'f';
    }

    private boolean isOperator(char c) {
        return c == '&' || c == '|' || c == '!';
    }
}
