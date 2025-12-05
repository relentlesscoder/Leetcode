package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/29/2019.
 * #1209 https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 */
public class RemoveAllAdjacentDuplicatesInStringII {

    // time O(n), space O(n)
    public String removeDuplicates(String s, int k) {
        int n = s.length();
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && stack.peek()[0] == c) {
                stack.peek()[1]++;
            } else {
                stack.push(new int[]{c, 1});
            }
            if (stack.peek()[1] == k) {
                stack.pop();
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            int[] curr = stack.pollLast();
            int count = curr[1];
            while (count-- > 0) {
                res.append((char) curr[0]);
            }
        }
        return res.toString();
    }
}


