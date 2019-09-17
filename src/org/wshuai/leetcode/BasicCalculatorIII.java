package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 9/17/19.
 * #772 https://leetcode.com/problems/basic-calculator-iii/
 */
public class BasicCalculatorIII {
    public int calculate(String s) {
        LinkedList<String> stack = new LinkedList<>();
        int i = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            if(c == '*' || c == '/' || c == '+' || c == '-'){
                stack.offerLast("" + c);
            }else if(Character.isDigit(c)){
                int j = i+1;
                while(j < s.length() && Character.isDigit(s.charAt(j))){
                    j++;
                }
                String num = s.substring(i, j);
                if(!stack.isEmpty() && (stack.peekLast().equals("*") || stack.peekLast().equals("/"))){
                    String opr = stack.pollLast();
                    long last = Long.parseLong(stack.pollLast());
                    long curr = Long.parseLong(num);
                    long result = opr.equals("*") ? last*curr : last/curr;
                    stack.offerLast("" + result);
                }else{
                    stack.offerLast(num);
                }
                i = j - 1;
            }else if(c == '('){
                int count = 1;
                int j = i+1;
                while(j < s.length() && count != 0){
                    if(s.charAt(j) == '('){
                        count++;
                    }else if(s.charAt(j) == ')'){
                        count--;
                    }
                    j++;
                }
                int mid = calculate(s.substring(i+1, j-1));
                if(!stack.isEmpty() && (stack.peekLast().equals("*") || stack.peekLast().equals("/"))){
                    String opr = stack.pollLast();
                    long last = Long.parseLong(stack.pollLast());
                    long curr = mid;
                    long result = opr.equals("*") ? last*curr : last/curr;
                    stack.offerLast("" + result);
                }else{
                    stack.offerLast("" + mid);
                }
                i = j-1;
            }
            i++;
        }

        while(!stack.isEmpty() && stack.size() > 1){
            long first = stack.peekFirst().equals("-") ? 0L : Long.parseLong(stack.pollFirst());
            String opr = stack.pollFirst();
            long second = Long.parseLong(stack.pollFirst());
            long result = opr.equals("+") ? first+second : first-second;
            stack.offerFirst("" + result);
        }

        return Integer.parseInt(stack.poll());
    }
}
