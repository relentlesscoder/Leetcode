package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #424 https://leetcode.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharacterReplacement {
  public int characterReplacement(String s, int k) {
    if(s == null || s.isEmpty() || k < 0){
      return 0;
    }
    int left = 0;
    int len = s.length();
    int max = 0;
    int[] aux = new int[26];
    int maxCnt = 0;
    for(int i = 0; i < len; i++){
      char val = s.charAt(i);
      int idx = val-'A';
      aux[idx]++;
      maxCnt = Math.max(aux[idx], maxCnt);
      while(i-left+1-maxCnt > k){
        aux[s.charAt(left)-'A']--;
        int cMax = 0;
        for(int j = 0; j < 26; j++){
          cMax = Math.max(cMax, aux[j]);
        }
        maxCnt = cMax;
        left++;
      }
      max = Math.max(i-left+1, max);
    }
    return max;
  }
}
