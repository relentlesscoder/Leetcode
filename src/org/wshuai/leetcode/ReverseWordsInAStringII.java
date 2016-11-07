package org.wshuai.leetcode;

/**
 * Created by Wei on 11/7/2016.
 * #186 https://leetcode.com/problems/reverse-words-in-a-string-ii/
 */
public class ReverseWordsInAStringII {
  public void reverseWords(char[] s) {
    if(s == null || s.length == 0){
      return;
    }
    int len = s.length;
    reverse(s, 0, len-1);
    int i = 0;
    while(i < len){
      int j = i;
      while(j < len && s[j] != ' '){
        j++;
      }
      reverse(s, i, j-1);
      i = j+1;
    }
  }

  private void reverse(char[] s, int start, int end){
    int left = start;
    int right = end;
    while(left < right){
      char tmp = s[left];
      s[left] = s[right];
      s[right] = tmp;
      left++;
      right--;
    }
  }
}
