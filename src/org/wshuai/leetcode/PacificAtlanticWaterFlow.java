package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/29/2016.
 * #417 https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
public class PacificAtlanticWaterFlow {
  public List<int[]> pacificAtlantic(int[][] matrix) {
    List<int[]> res = new ArrayList<int[]>();
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
      return res;
    }
    int[] vs = new int[]{0, 0, 1, -1};
    int[] hs = new int[]{1, -1, 0, 0};

    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][] aux = new int[rows][cols];

    //Pacific
    boolean[] visited = new boolean[rows*cols];
    Queue<Integer> cells = new LinkedList<Integer>();
    for(int i = 0; i < cols; i++){
      visited[i] = true;
      cells.offer(i);
      aux[0][i] += 1;
    }
    for(int i = 1; i < rows; i++){
      int idx = i*cols;
      visited[idx] = true;
      cells.offer(idx);
      aux[i][0] += 1;
    }
    pacificAtlanticDFS(cells, matrix, aux, visited, rows, cols, vs, hs);

    //Atlantic
    cells = new LinkedList<Integer>();
    Arrays.fill(visited, false);
    for(int i = 0; i < cols; i++){
      int idx = (rows-1)*cols+i;
      visited[idx] = true;
      cells.offer(idx);
      aux[rows-1][i] += 1;
    }
    for(int i = 0; i < rows-1; i++){
      int idx = (i+1)*cols-1;
      visited[idx] = true;
      cells.offer(idx);
      aux[i][cols-1] += 1;
    }
    pacificAtlanticDFS(cells, matrix, aux, visited, rows, cols, vs, hs);

    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        if(aux[i][j] == 2){
          int[] arr = new int[2];
          arr[0] = i;
          arr[1] = j;
          res.add(arr);
        }
      }
    }

    return res;
  }

  private void pacificAtlanticDFS(Queue<Integer> cells, int[][] matrix, int[][] aux, boolean[] visited, int rows, int cols, int[] vs, int[] hs){
    while(!cells.isEmpty()){
      int idx = cells.poll();
      int r = idx/cols;
      int c = idx%cols;
      for(int i = 0; i < 4; i++){
        int x = r + vs[i];
        int y = c + hs[i];
        int nidx = x*cols+y;
        if(x >= 0 && x < rows && y >=0 && y < cols && !visited[nidx]){
          if(matrix[x][y] >= matrix[r][c]){
            visited[nidx] = true;
            cells.offer(nidx);
            aux[x][y] += 1;
          }
        }
      }
    }
  }
}
