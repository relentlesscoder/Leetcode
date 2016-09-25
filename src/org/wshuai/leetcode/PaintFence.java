package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/20/2016.
 */
public class PaintFence {

  // Use dynamic programming
  public int numWaysDP(int n, int k) {
    if(n == 0 || k == 0){
      return 0;
    }
    if(n == 1){
      return k;
    }
    if(n == 2){
      return k*k;
    }
    int[] result = new int[n + 1];
    result[0] = 0;
    result[1] = k;
    result[2] = k*k;
    for(int i = 3; i <= n; i++){
      result[i] = (k - 1) * (result[i - 1] + result[i - 2]);
    }
    return result[n];
  }

  // Use back tracking
  public int numWaysBT(int n, int k) {
    if(k == 0 || n == 0){
      return 0;
    }
    List<List<Integer>> lst = new ArrayList<List<Integer>>();
    paintFence(0, n, k, lst, new ArrayList<Integer>());
    return lst.size();
  }

  private void paintFence(int count, int n, int k, List<List<Integer>> lst, List<Integer> curr){
    if(count == n){
      lst.add(curr);
      return;
    }else{
      int size = curr.size();
      if(count == 0){
        for(int i = 0; i < k; i++){
          List<Integer> nxt = new ArrayList<Integer>();
          nxt.add(i);
          paintFence(count + 1, n, k, lst, nxt);
        }
      }else{
        for(int i = 0; i < k; i++){
          if(count > 1 && curr.get(count - 1) ==  curr.get(count - 2) && i == curr.get(count - 1)){
            continue;
          }
          List<Integer> nxt = new ArrayList(curr);
          nxt.add(i);
          paintFence(count + 1, n, k, lst, nxt);
        }
      }
    }
  }
}
