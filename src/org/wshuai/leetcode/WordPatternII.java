package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 3/13/17.
 * #291 https://leetcode.com/problems/word-pattern-ii/
 */
public class WordPatternII {
  public boolean wordPatternMatch(String pattern, String str) {
    if(pattern == null || str == null){
      return false;
    }
    if(pattern.equals("") && str.equals("")){
      return true;
    }
    if(pattern.equals("")){
      return false;
    }
    if(str.equals("")){
      return false;
    }

    int i = 0;
    int j = 0;
    int pLen = pattern.length();
    int sLen = str.length();
    Map<Character, String> map = new HashMap<Character, String>();
    Set<String> set = new HashSet<String>();
    return wordPatternMatchUtil(i, j, pLen, sLen, map, set, pattern, str);
  }

  private boolean wordPatternMatchUtil(int i, int j, int pLen, int sLen,
                                       Map<Character, String> map, Set<String> set,
                                       String pattern, String str){
    if(i == pLen && j == sLen){
      return true;
    }
    if(i >= pLen || j >= sLen){
      return false;
    }
    char c = pattern.charAt(i);
    if(map.containsKey(c)){
      String val = map.get(c);
      int len = val.length();
      if(j+len <= sLen){
        String nxt = str.substring(j, j+len);
        if(nxt.equals(val)){
          return wordPatternMatchUtil(i+1, j+len, pLen, sLen, map, set, pattern, str);
        }
      }
    }else{
      for(int k = j; k < sLen; k++){
        String val = str.substring(j, k+1);
        if(!set.contains(val)){
          map.put(c, val);
          set.add(val);
          if(wordPatternMatchUtil(i+1, k+1, pLen, sLen, map, set, pattern, str)){
            return true;
          }
          map.remove(c);
          set.remove(val);
        }
      }
    }
    return false;
  }
}
