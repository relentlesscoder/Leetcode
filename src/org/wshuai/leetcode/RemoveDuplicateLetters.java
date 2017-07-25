package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 7/24/2017.
 * #316 https://leetcode.com/problems/remove-duplicate-letters/
 */
public class RemoveDuplicateLetters {
  public String removeDuplicateLetters(String s) {
    if(s == null || s.isEmpty()){
      return "";
    }
    Stack<Character> stack = new Stack<Character>();
    int[] count = new int[26];
    char[] arr = s.toCharArray();
    for(char c: arr){
      count[c-'a']++;
    }
    boolean[] visited = new boolean[26];
    for(char c: arr){
      count[c-'a']--;
      if(visited[c-'a']){
        continue;
      }
      while(!stack.isEmpty() && stack.peek() > c && count[stack.peek()-'a'] > 0){
        visited[stack.peek()-'a'] = false;
        stack.pop();
      }
      stack.push(c);
      visited[c-'a'] = true;
    }
    StringBuilder sb = new StringBuilder();
    for(char c: stack){
      sb.append(c);
    }
    return sb.toString();
  }
}
