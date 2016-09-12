package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 8/28/16.
 */
public class ValidAnagram {
  public static boolean isAnagram(String s, String t) {
    if(s == null || t == null){
      return false;
    }
    if(s.isEmpty() && t.isEmpty()){
      return true;
    }
    if(s.length() != t.length()){
      return false;
    }

    Map<Character, Integer> mp = new HashMap<Character, Integer>();
    char[] ss = s.toCharArray();
    char[] ts = t.toCharArray();
    int len = ss.length;
    for(int i = 0; i < len; i++){
      char x = ss[i];
      if(mp.containsKey(x)){
        int val = mp.get(x);
        val++;
        mp.put(x, val);
      }else{
        mp.put(x, 1);
      }
    }

    for(int i = 0; i < len; i++){
      char x = ts[i];
      if(!mp.containsKey(x)){
        return false;
      }else{
        int val = mp.get(x);
        val--;
        if(val == 0){
          mp.remove(x);
        }else{
          mp.put(x, val);
        }
      }
    }

    return mp.size() == 0;
  }
}
