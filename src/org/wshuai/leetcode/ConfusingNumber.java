package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 8/20/19.
 * #1056 https://leetcode.com/problems/confusing-number/
 */
public class ConfusingNumber {
    public boolean confusingNumber(int N) {
        List<Integer> arr = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while(N != 0){
            int curr = N % 10;
            arr.add(curr);
            if(curr == 2
                    || curr == 3
                    || curr == 4
                    || curr == 5
                    || curr == 7){
                return false;
            }else if(curr == 6){
                stack.push(9);
            }else if(curr == 9){
                stack.push(6);
            }else{
                stack.push(curr);
            }
            N /= 10;
        }
        for(int i: arr){
            if(stack.pop() != i){
                return true;
            }
        }
        return false;
    }
}
