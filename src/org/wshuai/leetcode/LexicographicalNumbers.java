package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/19/16.
 * #386 https://leetcode.com/problems/lexicographical-numbers/
 */
public class LexicographicalNumbers {
  //DFS
  public List<Integer> lexicalOrder(int n) {
    List<Integer> lst = new ArrayList<Integer>();
    if(n < 1){
      return lst;
    }
    for(int i = 1; i <= 9; i++){
      lexicalOrderUtil(i, n, lst);
    }
    return lst;
  }

  private void lexicalOrderUtil(int curr, int n, List<Integer> lst){
    if(curr > n){
      return;
    }
    lst.add(curr);
    for(int i = 0; i <= 9; i++){
      int nxt = curr*10+i;
      if(nxt <= n){
        lexicalOrderUtil(nxt, n, lst);
      }
    }
  }
}
