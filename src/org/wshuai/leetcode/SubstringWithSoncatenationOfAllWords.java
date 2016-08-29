package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 8/17/2016.
 */
public class SubstringWithSoncatenationOfAllWords {
  public static List<Integer> findSubstringBrutalForce(String s, String[] words) {
    List<Integer> lst = new ArrayList<Integer>();
    if(s == null || s.isEmpty() || words == null || words.length == 0){
      return lst;
    }

    int wLen = words[0].length();
    int wNum = words.length;
    int sLen = s.length();

    for(int i = 0; i < sLen; i++){
      int index = i + wLen*wNum;
      String x = "";
      if(index >= sLen){
        x = s.substring(i);
      }else{
        x = s.substring(i, index);
      }
      if(checkStr(x, words, wLen, wNum)){
        lst.add(i);
      }
    }

    return lst;
  }

  public static boolean checkStr(String x, String[] words, int wLen, int wNum){
    int sLen = wLen*wNum;
    if(x.length() < sLen){
      return false;
    }
    boolean r = true;
    Map<String, Integer> m = new HashMap<String, Integer>();
    for(int i = 0; i < wNum; i++){
      String y = words[i];
      if(m.containsKey(y)){
        m.put(y, m.get(y)+1);
      }else{
        m.put(y, 1);
      }
    }
    for(int i = 0; i < sLen; i += wLen){
      String s = "";
      if(i + wLen >= sLen){
        s = x.substring(i);
      }else{
        s = x.substring(i, i + wLen);
      }
      if(!m.containsKey(s)){
        return false;
      }else{
        m.put(s, m.get(s)-1);
      }
    }
    for(Integer i: m.values()){
      if(i != 0){
        return false;
      }
    }

    return r;
  }
}
