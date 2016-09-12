package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 8/26/16.
 */
public class EvalRPN {
  public static int evalRPN(String[] tokens) {
    if(tokens == null){
      return 0;
    }
    int len = tokens.length;
    if(len == 1){
      return Integer.parseInt(tokens[0]);
    }
    if(len < 3){
      return 0;
    }
    Stack<Integer> s = new Stack<Integer>();
    int result = 0;
    int i = 0;
    while(i < len){
      String str = tokens[i];
      if(isOpt(str)){
        int right = s.pop();
        int left = s.pop();
        result = calRPN(str, left, right);
        s.push(result);
      }else{
        try{
          s.push(Integer.parseInt(str));
        }
        catch(NumberFormatException e){
          throw new NumberFormatException("Invalid format." + e.getMessage());
        }
      }
      i++;
    }
    return result;
  }

  private static boolean isOpt(String str){
    return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
  }

  private static int calRPN(String opt, int left, int right){
    int result = 0;
    switch(opt.charAt(0)){
      case '+':
        result = left + right;
        break;
      case '-':
        result = left - right;
        break;
      case '*':
        result = left * right;
        break;
      case '/':
        result = left / right;
        break;
      default:
        throw new IllegalArgumentException("Invalid operator.");
    }
    return result;
  }
}
