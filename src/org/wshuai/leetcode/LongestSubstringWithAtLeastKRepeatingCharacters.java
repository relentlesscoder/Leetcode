package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/16.
 * #395 https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
  //O
  public int longestSubstring(String s, int k) {
    if(s == null || s.isEmpty()){
      return 0;
    }
    int i = 0;
    int len = s.length();
    int max = 0;
    while(i+k <= len){
      int[] aux = new int[26];
      int mask = 0;
      int mIdx = i;
      for(int j = i; j < len; j++){
        int idx = s.charAt(j)-'a';
        aux[idx]++;
        if(aux[idx] < k){
          mask |= (1 << idx);
        }else{
          mask &= ~(1 << idx);
        }
        if(mask == 0){
          int cLen = j-i+1;
          max = cLen > max ? cLen : max;
          mIdx = j;
        }
      }
      i = mIdx+1;
    }
    return max;
  }
}
