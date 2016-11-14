package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/16.
 * #131 https://leetcode.com/problems/palindrome-partitioning/
 */
public class PalindromePartitioning {
  //O(n^2), DFS & DP
  public List<List<String>> partition(String s) {
    List<List<String>> lst = new ArrayList<List<String>>();
    if(s == null || s.isEmpty()){
      return lst;
    }
    int len = s.length();
    boolean[][] aux = new boolean[len][len];
    for(int k = 0; k < len; k++){
      for(int i = 0; i+k < len; i++){
        int j = i+k;
        aux[i][j] = (i==j) || (s.charAt(i) == s.charAt(j)
          && (i+1 == j || aux[i+1][j-1]));
      }
    }
    partitionUtil(s, len, lst, aux, 0, new ArrayList<String>());
    return lst;
  }

  private void partitionUtil(String s, int len, List<List<String>> lst, boolean[][] aux,
                             int i, List<String> curr){
    if(i == len){
      List<String> val = new ArrayList<String>(curr);
      lst.add(val);
    }else{
      for(int j = i; j < len; j++){
        if(aux[i][j]){
          curr.add(s.substring(i, j+1));
          partitionUtil(s, len, lst, aux, j+1, curr);
          curr.remove(curr.size()-1);
        }
      }
    }
  }
}
