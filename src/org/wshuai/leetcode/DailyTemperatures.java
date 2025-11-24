package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/06/2019.
 * #0739 https://leetcode.com/problems/daily-temperatures/
 */
public class DailyTemperatures {

    // time O(n), space O(n)
    public int[] dailyTemperaturesRTL(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(n);
        for (int i = n - 1; i >= 0; i--) {
            int temp = temperatures[i];
            while (stack.size() > 1 && temperatures[stack.peek()] <= temp) {
                stack.pop();
            }
            res[i] = stack.peek() == n ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    // time O(n), space O(n)
    public int[] dailyTemperaturesLTR(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(n);
        for (int i = 0; i < n; i++) {
            int temp = temperatures[i];
            while (stack.size() > 1 && temperatures[stack.peek()] < temp) {
                res[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}
