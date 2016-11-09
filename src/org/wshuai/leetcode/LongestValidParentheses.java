package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 11/8/16.
 * #32 https://leetcode.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {
  //O(n), DP 20ms
  public int longestValidParenthesesDP(String s) {
    if(s == null || s.length() == 0){
      return 0;
    }
    int len = s.length();
    int max = 0;
    int[] aux = new int[len];
    for(int i = len-2; i >= 0; i--){
      if(s.charAt(i) == '('){
        //Handle case like "(())"
        int end = i+aux[i+1]+1;
        if(end < len && s.charAt(end) == ')'){
          aux[i] = aux[i+1]+2;
          if(end < len-1){
            //Handle case like "()()"
            aux[i] += aux[end+1];
          }
        }
      }
      max = aux[i] > max ? aux[i] : max;
    }
    return max;
  }

  //O(n), TLE for large inputs
  public int longestValidParenthesesStack(String s) {
    if(s == null || s.isEmpty()){
      return 0;
    }
    int max = 0;
    Stack<PrtIndex> stack = new Stack<PrtIndex>();
    int len = s.length();
    for(int i = 0; i < len; i++){
      if(s.charAt(i) == '('){
        stack.push(new PrtIndex('(', i));
      }else{
        if(!stack.isEmpty() && stack.peek().val == '('){
          int cLen = 0;
          stack.pop();
          if(stack.isEmpty()){
            cLen = i+1;
          }else{
            cLen = i-stack.peek().index;
          }
          max = cLen > max ? cLen : max;
        }else{
          stack.push(new PrtIndex(')', i));
        }
      }
    }
    return max;
  }
}

class PrtIndex{
  int index;
  char val;
  public PrtIndex(char val, int index){
    this.index = index;
    this.val = val;
  }
}
