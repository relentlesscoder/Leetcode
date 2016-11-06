package org.wshuai.leetcode;

/**
 * Created by Wei on 11/5/16.
 * #161 https://leetcode.com/problems/one-edit-distance/
 */
public class OneEditDistance {
  //O(n)
  public boolean isOneEditDistance(String s, String t) {
    if(s == null || t == null){
      return false;
    }
    if(s.equals(t)){
      return false;
    }
    int sLen = s.length();
    int tLen = t.length();
    boolean sMin = sLen < tLen;
    int diff = Math.abs(sLen-tLen);
    if(diff > 1){
      return false;
    }
    int i = 0;
    int j = 0;
    boolean sec = false;
    while(i < sLen || j < tLen){
      if(i < sLen && j < tLen && s.charAt(i) == t.charAt(j)){
        i++;
        j++;
      }else{
        if(sec){
          return false;
        }
        if(diff == 0){
          i++;
          j++;
        }else if(sMin){
          j++;
        }else{
          i++;
        }
        sec = true;
      }
    }
    return true;
  }
}
