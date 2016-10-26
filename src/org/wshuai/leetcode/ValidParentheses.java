package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/26/2016.
 * #20 https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {
  public boolean isValid(String s) {
    if(s == null || s.isEmpty()){
      return false;
    }
    int len = s.length();
    if(len%2 == 1){
      return false;
    }
    char first = s.charAt(0);
    if(first != '(' && first != '{' && first != '['){
      return false;
    }
    Stack<Character> stk = new Stack<Character>();
    for(int i = 0; i < len; i++){
      char x = s.charAt(i);
      if(x == '(' || x == '{' || x == '['){
        stk.push(x);
      }else{
        if(stk.empty()){
          return false;
        }else{
          char y = stk.pop();
          if(y == '(' && x == ')'){
            continue;
          }
          else if(y == '[' && x == ']'){
            continue;
          }
          else if(y == '{' && x == '}'){
            continue;
          }
          else{
            return false;
          }
        }
      }
    }
    if(!stk.empty()){
      return false;
    }
    return true;
  }
}
