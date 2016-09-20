package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/19/2016.
 */
public class PalindromePermutation {
  public boolean canPermutePalindrome(String s) {
    if(s == null || s.isEmpty()){
      return true;
    }

    char[] chrs = s.toCharArray();
    int len = chrs.length;
    Map<Character, Integer> vMap = new HashMap<Character, Integer>();
    for(int i = 0; i < len; i++){
      char x = chrs[i];
      if(vMap.containsKey(x)){
        int c = vMap.get(x);
        vMap.put(x, c + 1);
      }else{
        vMap.put(x, 1);
      }
    }

    boolean isEven = len%2 == 0 ? true : false;
    int count = 0;
    for(char c: vMap.keySet()){
      int val = vMap.get(c);
      if(val%2 != 0){
        count++;
      }
    }
    if(isEven && count == 0){
      return true;
    }
    if(!isEven && count == 1){
      return true;
    }
    return false;
  }
}
