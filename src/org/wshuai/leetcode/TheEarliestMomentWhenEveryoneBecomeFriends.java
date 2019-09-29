package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 9/29/2019.
 * #1101 https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/
 */
public class TheEarliestMomentWhenEveryoneBecomeFriends {
  // union find
  public int earliestAcq(int[][] logs, int N) {
    int[] root = new int[N];
    int[] size = new int[N];
    for(int i = 0; i < N; i++){
      root[i] = i;
    }
    Arrays.fill(size, 1);
    Arrays.sort(logs, (a, b) -> a[0] - b[0]);
    for(int[] log: logs){
      int root1 = findRoot(root, log[1]);
      int root2 = findRoot(root, log[2]);
      if(root1 == root2){
        continue;
      }
      if(size[root1] >= size[root2]){
        size[root1] += size[root2];
        root[root2] = root1;
        if(size[root1] == N){
          return log[0];
        }
      }else{
        size[root2] += size[root1];
        root[root1] = root2;
        if(size[root2] == N){
          return log[0];
        }
      }
    }
    return -1;
  }

  private int findRoot(int[] root, int curr){
    while(curr != root[curr]){
      curr = root[curr];
    }
    return curr;
  }
}


