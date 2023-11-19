package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/29/2019.
 * #1209 https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 */
public class RemoveAllAdjacentDuplicatesInStringII {

    // time O(n), space O(n)
    public String removeDuplicates(String s, int k) {
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = (!stack.isEmpty() && stack.peek()[0] == c - 'a') ? stack.peek()[1] + 1 : 1;
            if (count == k) {
                int delete = k;
                while (--delete > 0) {
                    stack.pop();
                }
            } else {
                stack.push(new int[]{c - 'a', count});
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int[] cur : stack) {
            sb.append((char) (cur[0] + 'a'));
        }
        return sb.toString();
    }
}


