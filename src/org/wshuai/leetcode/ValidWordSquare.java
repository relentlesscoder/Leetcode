package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 10/25/16.
 * #422 https://leetcode.com/problems/valid-word-square/
 */
public class ValidWordSquare {
  public boolean validWordSquare(List<String> words) {
    if(words == null || words.size() == 0){
      return false;
    }

    int len = words.size();
    char[][] aux = new char[len][len];
    for(int i = 0; i < len; i++){
      String val = words.get(i);
      int sLen = val.length();
      if(sLen > len){
        return false;
      }
      for(int j = 0; j < len; j++){
        if(j < sLen){
          aux[i][j] = val.charAt(j);
        }else{
          aux[i][j] = ' ';
        }
      }
    }

    boolean res = true;
    for(int i = 0; i < len; i++){
      for(int j = i+1; j < len; j++){
        if(aux[i][j] != aux[j][i]){
          return false;
        }
      }
    }
    return res;
  }
}
