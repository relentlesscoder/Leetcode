package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/11/16.
 * #267 https://leetcode.com/problems/palindrome-permutation-ii/
 */
public class PalindromePermutationII {
  public List<String> generatePalindromes(String s) {
    List<String> lst = new ArrayList<String>();
    if(s == null || s.isEmpty()){
      return lst;
    }
    int len = s.length();
    char sig = ' ';
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i = 0; i < len; i++){
      char key = s.charAt(i);
      if(map.containsKey(key)){
        map.put(key, map.get(key)+1);
      }else{
        map.put(key, 1);
      }
    }
    List<Character> chars = new ArrayList<Character>();
    for(Map.Entry<Character, Integer> entry: map.entrySet()){
      int cnt = entry.getValue();
      char key = entry.getKey();
      if(cnt%2 == 0){
        int c = cnt/2;
        while(c > 0){
          chars.add(key);
          c--;
        }
      }else if(sig == ' '){
        sig = key;
        int c = cnt/2;
        while(c > 0){
          chars.add(key);
          c--;
        }
      }else{
        return lst;
      }
    }

    int nLen = chars.size();
    boolean[] aux = new boolean[nLen];
    generatePalindromesUtil(chars, sig, aux, new ArrayList<Character>(), nLen, lst);
    return lst;
  }

  //DFS
  private void generatePalindromesUtil(List<Character> chars, char sig, boolean[] used, List<Character> curr, int len, List<String> lst){
    if(curr.size() == len){
      StringBuilder sb = new StringBuilder();
      for(char val: curr){
        sb.append(val);
      }
      String r = sb.toString() + (sig == ' ' ? "" : sig) + sb.reverse().toString();
      lst.add(r);
    }else{
      for(int i = 0; i < len; i++){
        if(used[i]){
          continue;
        }
        if(i > 0 && chars.get(i) == chars.get(i-1) && !used[i-1]){
          continue;
        }
        used[i] = true;
        curr.add(chars.get(i));
        generatePalindromesUtil(chars, sig, used, curr, len, lst);
        curr.remove(curr.size()-1);
        used[i] = false;
      }
    }
  }
}
