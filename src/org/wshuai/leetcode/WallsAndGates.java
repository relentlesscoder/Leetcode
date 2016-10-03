package org.wshuai.leetcode;

/**
 * Created by Wei on 10/1/2016.
 */
public class WallsAndGates {

  // Use DFS
  public void wallsAndGatesDFS(int[][] rooms) {
    if(rooms == null){
      return;
    }
    int rLen = rooms.length;
    if(rLen <= 0){
      return;
    }
    int cLen = rooms[0].length;
    if(cLen <= 0){
      return;
    }

    for(int i = 0; i < rLen; i++){
      for(int j = 0; j < cLen; j++){
        int val = rooms[i][j];
        if(val == 0){
          boolean[] visited = new boolean[rLen*cLen];
          wallsAndGatesBFS(rooms, i + 1, j, visited, rLen, cLen, 1);
          wallsAndGatesBFS(rooms, i - 1, j, visited, rLen, cLen, 1);
          wallsAndGatesBFS(rooms, i, j + 1, visited, rLen, cLen, 1);
          wallsAndGatesBFS(rooms, i, j - 1, visited, rLen, cLen, 1);
        }
      }
    }
  }

  private void wallsAndGatesBFS(int[][] rooms, int i, int j, boolean[] visited, int rLen, int cLen, int dis){
    if(i < 0 || j < 0 || i >= rLen || j >= cLen || rooms[i][j] <= 0){
      return;
    }
    int cord = i*cLen + j;
    if(visited[cord]){
      return;
    }

    int val = rooms[i][j];
    visited[cord] = true;
    rooms[i][j] = dis < val ? dis : val;
    wallsAndGatesBFS(rooms, i + 1, j, visited, rLen, cLen, dis+1);
    wallsAndGatesBFS(rooms, i - 1, j, visited, rLen, cLen, dis+1);
    wallsAndGatesBFS(rooms, i, j + 1, visited, rLen, cLen, dis+1);
    wallsAndGatesBFS(rooms, i, j - 1, visited, rLen, cLen, dis+1);
    visited[cord] = false;
  }
}
