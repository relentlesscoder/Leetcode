package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/3/16.
 */
public class LongestPalindrome {
  public int longestPalindrome(String s) {
    if(s == null || s.isEmpty()){
      return 0;
    }

    int[] counts = new int[256];
    Arrays.fill(counts, 0);
    int len = s.length();
    for(int i = 0; i < len; i++){
      counts[s.charAt(i)]++;
    }
    int max = 0;
    for(int i = 0; i < 256; i++){
      int cnt = counts[i];
      if(cnt > 0){
        if(cnt%2 == 0){
          max+=cnt;
        }else{
          max+=cnt-1;
        }
      }
    }
    if(max != len){
      max++;
    }
    return max;
  }
}
