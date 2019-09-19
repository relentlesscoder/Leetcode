package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 9/19/19.
 * #946 https://leetcode.com/problems/validate-stack-sequences/
 */
public class ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        int i = 0;
        int j = 0;
        Stack<Integer> stack = new Stack<>();
        while(i < len && j < len){
            stack.push(pushed[i]);
            i++;
            while(!stack.isEmpty() && stack.peek() == popped[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
