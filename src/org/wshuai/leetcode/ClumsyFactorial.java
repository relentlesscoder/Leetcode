package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/24/2019.
 * #1006 https://leetcode.com/problems/clumsy-factorial/
 */
public class ClumsyFactorial {

    // time O(n), space O(1)
    public int clumsyMath(int n) {
        int res = 0, num = n, count = n / 4, rem = n % 4, curr = 0;
        while (curr++ < count) {
            int val = (res == 0 ? num : -num) * (num - 1) / (num - 2);
            res += val + num - 3;
            num -= 4;
        }
        if (rem == 1) {
            res += count == 0 ? num : -num;
        }
        if (rem == 2) {
            int val = num * (num - 1);
            res += count == 0 ? val : -val;
        }
        if (rem == 3) {
            int val = num * (num - 1) / (num - 2);
            res += count == 0 ? val : -val;
            ;
        }
        return res;
    }

    // time O(n), space O(n)
    public int clumsyStack(int n) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(n);
        for (int i = n - 1, j = 0; i >= 1; i--, j++) {
            j %= 4;
            if (j == 0) {
                stack.push(stack.pop() * i);
            } else if (j == 1) {
                stack.push(stack.pop() / i);
            } else if (j == 2) {
                stack.push(i);
            } else {
                stack.push(-i);
            }
        }
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
