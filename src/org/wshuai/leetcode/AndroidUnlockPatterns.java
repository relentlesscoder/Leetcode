package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/13/16.
 * #351 https://leetcode.com/problems/android-unlock-patterns/
 */
public class AndroidUnlockPatterns {
  public int numberOfPatterns(int m, int n) {
    int[][] aux = new int[10][10];
    aux[1][3] = aux[3][1] = 2;
    aux[3][9] = aux[9][3] = 6;
    aux[9][7] = aux[7][9] = 8;
    aux[7][1] = aux[1][7] = 4;
    aux[4][6] = aux[6][4] = 5;
    aux[2][8] = aux[8][2] = 5;
    aux[1][9] = aux[9][1] = aux[3][7] = aux[7][3] = 5;
    boolean[] visited = new boolean[9];
    CountObj co = new CountObj();
    numberOfPatternsUtil(aux, visited, m, n, new ArrayList<Integer>(), co);
    return co.count;
  }

  private void numberOfPatternsUtil(int[][] aux, boolean[] visited, int m, int n, List<Integer> curr, CountObj co){
    int size = curr.size();
    if(size >= m){
      co.count++;
    }
    if(size == n){
      return;
    }
    int last = size == 0 ? -1 : curr.get(size-1);
    for(int i = 0; i < 9; i++){
      if(last == -1 || (!visited[i] && (aux[last][i+1] == 0 || visited[aux[last][i+1]-1]))){
        visited[i] = true;
        curr.add(i+1);
        numberOfPatternsUtil(aux, visited, m, n, curr, co);
        visited[i] = false;
        curr.remove(curr.size()-1);
      }
    }
  }
}
