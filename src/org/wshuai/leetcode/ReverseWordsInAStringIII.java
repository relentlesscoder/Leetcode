package org.wshuai.leetcode;

/**
 * Created by Wei on 7/17/17.
 * #557 https://leetcode.com/problems/reverse-words-in-a-string-iii/
 */
public class ReverseWordsInAStringIII {
  public String reverseWords(String s) {
    if(s == null || s.isEmpty()){
      return "";
    }
    StringBuilder sb = new StringBuilder();
    int i = 0;
    int j = 0;
    while(j < s.length()){
      if(s.charAt(j) != ' '){
        j++;
      }else{
        String word = (new StringBuilder(s.substring(i, j))).reverse().toString();
        sb.append(word);
        sb.append(" ");
        i = j+1;
        j = i;
      }
    }
    sb.append((new StringBuilder(s.substring(i, j))).reverse().toString());
    return sb.toString();
  }
}
