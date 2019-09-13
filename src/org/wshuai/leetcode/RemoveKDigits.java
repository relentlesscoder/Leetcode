package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 9/13/2019.
 * #402 https://leetcode.com/problems/remove-k-digits/
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        for(char c: num.toCharArray()){
            while(stack.size() > 0 && k > 0 && stack.peekLast() > c){
                stack.pollLast();
                k--;
            }
            stack.offer(c);
        }

        for(int i = 0; i < k; i++){
            stack.pollLast();
        }

        StringBuilder sb = new StringBuilder();
        boolean toSkip = true;
        for(char c: stack){
            if(toSkip && c == '0'){
                continue;
            }
            toSkip = false;
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
