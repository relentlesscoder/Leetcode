package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 9/21/2016.
 */
public class LongestAbsoluteFilePath {
  public int lengthLongestPath(String input) {
    int max = 0;
    if(input == null || input.length() == 0){
      return max;
    }

    Stack<Integer> stk = new Stack<Integer>();
    char[] chs = input.toCharArray();
    int len = chs.length;
    int i = 0;
    int depth = 0;
    int cLen = 0;

    // root folder
    while(i < len && chs[i] != '\n'){
      i++;
    }
    String root =  input.substring(0, i);
    int rLen = root.length();
    cLen += rLen;
    stk.push(rLen);
    if(root.contains(".")){
      max = cLen > max ? cLen : max;
    }

    while(i < len){
      i++;
      int count = 0;
      while(i < len && chs[i] == '\t'){
        i++;
        count++;
      }
      if(count == 0){
        count = 1;
      }

      while(i < len && chs[i] == ' '){
        i++;
      }

      int j = i;
      while(j < len && chs[j] != '\n'){
        j++;
      }

      String val =  input.substring(i, j);
      int vLen = val.length() + 1;
      while(count <= depth){
        int xLen = stk.pop();
        cLen -= xLen;
        depth--;
      }
      cLen += vLen;
      stk.push(vLen);
      depth++;
      if(val.contains(".")){
        max = cLen > max ? cLen : max;
      }
      i = j;
    }
    return max;
  }
}
