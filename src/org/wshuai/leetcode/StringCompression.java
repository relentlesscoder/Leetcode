package org.wshuai.leetcode;

/**
 * Created by Wei on 9/18/2019.
 * #443 https://leetcode.com/problems/string-compression/
 */
public class StringCompression {
  public int compress(char[] chars) {
    int len = chars.length;
    int i = 1;
    int j = 1;
    char s = chars[0];
    int count = 1;
    while(i < len){
      if(chars[i] != s){
        if(count != 1){
          int val = count;
          String str = "" + val;
          for(char c: str.toCharArray()){
            chars[j++] = c;
          }
        }
        count = 1;
        s = chars[i];
        chars[j++] = chars[i];
      }else{
        count++;
      }
      i++;
    }
    if(count != 1){
      int val = count;
      String str = "" + val;
      for(char c: str.toCharArray()){
        chars[j++] = c;
      }
    }
    return j;
  }
}
