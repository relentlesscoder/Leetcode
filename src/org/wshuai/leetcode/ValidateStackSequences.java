package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/19/2019.
 * #0946 https://leetcode.com/problems/validate-stack-sequences/
 */
public class ValidateStackSequences {

    // time O(n), space O(1)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int k = 0;
        // Use pushed as stack
        for (int i = 0, j = 0; i < n && j < n; i++) {
            pushed[k++] = pushed[i]; // k would never pass i
            int l = k - 1;
            while (l >= 0 && pushed[l] == popped[j]) {
                l--;
                j++;
            }
            k = l + 1;
        }
        return k == 0;
    }

    // time O(n), space O(n)
    public boolean validateStackSequencesStack(int[] pushed, int[] popped) {
        int n = pushed.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n && j < n; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
