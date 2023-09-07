package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Wei on 09/07/2023.
 * #2355 https://leetcode.com/problems/maximum-number-of-books-you-can-take/description/
 */
public class MaximumNumberOfBooksYouCanTake {

    // time O(n), space O(n)
    // https://zhuanlan.zhihu.com/p/630214203
    public long maximumBooks(int[] books) {
        int[] index = new int[books.length];
        Arrays.fill(index, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = books.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && books[stack.peek()] - stack.peek() >= books[i] - i) {
                index[stack.pop()] = i;
            }
            stack.push(i);
        }
        long[] dp = new long[books.length];
        dp[0] = books[0];
        long res = dp[0];
        long start = 0;
        for (int i = 1; i < books.length; i++) {
            if (index[i] == -1) {
                start = Math.max(1, books[i] - i);
                dp[i] = (start + books[i]) * (books[i] - start + 1) / 2;
            } else {
                start = Math.max(1, books[i] - (i - index[i]) + 1);
                dp[i] = dp[index[i]] + (start + books[i]) * (books[i] - start + 1) / 2;
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
