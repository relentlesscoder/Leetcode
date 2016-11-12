package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/16.
 * #320 https://leetcode.com/problems/generalized-abbreviation/
 */
public class GeneralizedAbbreviation {

  //DFS
  public List<String> generateAbbreviationsDFS(String word) {
    List<String> lst = new ArrayList<String>();
    if(word == null || word.length() == 0){
      lst.add("");
      return lst;
    }
    int len = word.length();
    generateAbbreviationsUtil(word, "", 0, lst, len);
    return lst;
  }

  private void generateAbbreviationsUtil(String word, String curr, int start, List<String> lst, int len){
    lst.add(curr + word.substring(start));
    if(start == len){
      return;
    }
    int i = 0;
    if(start > 0){
      i = start+1;
    }
    for(; i < len; i++){
      String prefix = curr + word.substring(start, i);
      for(int j = 1; j <= len-i; j++){
        generateAbbreviationsUtil(word, prefix+j, i+j, lst, len);
      }
    }
  }

  public List<String> generateAbbreviations(String word) {
    List<String> lst = new ArrayList<String>();
    if(word == null || word.length() == 0){
      lst.add("");
      return lst;
    }
    int len = word.length();
    int num = (int)Math.pow(2, len);
    StringBuilder val;
    for(int i = 0; i < num; i++){
      val = new StringBuilder();
      int j = len-1;
      int n = i;
      int cnt = 0;
      while(j >= 0){
        if((n&1) == 0){
          if(cnt > 0){
            val.insert(0, Integer.toString(cnt));
            cnt = 0;
          }
          val.insert(0, word.charAt(j));
        }else{
          cnt++;
        }
        n >>= 1;
        j--;
      }
      if(cnt > 0){
        val.insert(0, Integer.toString(cnt));
      }
      lst.add(val.toString());
    }
    return lst;
  }
}
