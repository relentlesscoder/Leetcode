package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/29/16.
 * #438 https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagramsInAString {

  //Use hashmap
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> lst = new ArrayList<Integer>();
    if(s == null || s.isEmpty() || p == null || p.isEmpty()){
      return lst;
    }

    Map<Character, Integer> map = new HashMap<Character, Integer>();
    int pLen = p.length();
    for(int i = 0; i < pLen; i++){
      char val = p.charAt(i);
      if(map.containsKey(val)){
        int count = map.get(val);
        map.put(val, count+1);
      }else{
        map.put(val, 1);
      }
    }

    int sLen = s.length();
    for(int i = 0; i < sLen; i++){
      char x = s.charAt(i);
      if(map.containsKey(x)){
        int count = map.get(x);
        map.put(x, count-1);
      }

      if(i >= pLen-1){
        if(i > pLen-1){
          int idx = i-pLen;
          char v = s.charAt(idx);
          if(map.containsKey(v)){
            int count = map.get(v);
            map.put(v, count+1);
          }
        }

        boolean isValid = true;
        for(int c: map.values()){
          if(c != 0){
            isValid = false;
            break;
          }
        }
        if(isValid){
          lst.add(i-pLen+1);
        }
      }
    }

    return lst;
  }
}
