package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 9/29/2019.
 * #1209 https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 */
public class RemoveAllAdjacentDuplicatesInStringII {
  public String removeDuplicates(String s, int k) {
    Stack<ValueCountPair<Character, Integer>> stack = new Stack<>();
    for(int i = 0; i < s.length(); i++){
      if(!stack.isEmpty() && s.charAt(i) == stack.peek().value){
        stack.peek().count++;
        if(stack.peek().count == k){
          stack.pop();
        }
      }else{
        ValueCountPair<Character, Integer> vc = new ValueCountPair(s.charAt(i), 1);
        stack.push(vc);
      }
    }
    StringBuilder sb = new StringBuilder();
    while(!stack.isEmpty()){
      ValueCountPair<Character, Integer> vc = stack.pop();
      int count = vc.count;
      while(count > 0){
        sb.append("" + vc.value);
        count--;
      }
    }
    return sb.reverse().toString();
  }
}


