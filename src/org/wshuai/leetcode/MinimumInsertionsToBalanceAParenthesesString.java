package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 08/11/2020.
 * #1541 https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
 */
public class MinimumInsertionsToBalanceAParenthesesString {

    // time O(n), space O(n)
    public int minInsertions(String s) {
        int res = 0;
        // 2 means (
        // 1 means ()
        Stack<Integer> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '('){
                if(stack.isEmpty() || stack.peek() == 2){
                    stack.push(2);
                }else{
                    stack.pop();
                    stack.push(2);
                    res++;
                }
            }else{
                if(stack.isEmpty()){
                    stack.push(1);
                    res++;
                }else if(stack.peek() == 1){
                    stack.pop();
                }else{
                    stack.pop();
                    stack.push(1);
                }
            }
        }
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
}
