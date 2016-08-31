package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by Wei on 8/21/16.
 */
public class WordBreak {
  public static boolean wordBreak(String s, Set<String> wordDict) {
    if(s == null || wordDict == null){
      return false;
    }
    int slen = s.length();
    int wlen = wordDict.size();
    if(slen == 0 || wlen == 0){
      return false;
    }
    int[] arr = new int[slen + 1];
    Arrays.fill(arr, -1);
    arr[0] = 0;

    for(int i = 0; i < slen; i++){
      if(arr[i] != -1){
        for(int j = i+1; j <= slen; j++){
          if(wordDict.contains(s.substring(i,j))){
            arr[j] = i;
          }
        }
      }
    }

    return (arr[slen] != -1);
  }
}
