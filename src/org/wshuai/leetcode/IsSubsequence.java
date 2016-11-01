package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/16.
 * #392 https://leetcode.com/problems/is-subsequence/
 */
public class IsSubsequence {
  public boolean isSubsequence(String s, String t) {
    if(s == null || t == null){
      return false;
    }

    int sLen = s.length();
    int tLen = t.length();
    int i = 0;
    int j = 0;
    while(i < tLen && j < sLen){
      if(t.charAt(i) == s.charAt(j)){
        i++;
        j++;
      }else{
        i++;
      }
    }

    return j == sLen;
  }
}
