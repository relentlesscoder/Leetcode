package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 7/20/2017.
 * #305 https://leetcode.com/problems/number-of-islands-ii/
 */
public class NumberOfIslandsII {
  //Union-Find
  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    List<Integer> res = new ArrayList<Integer>();
    if(m <= 0 || n <= 0 || positions == null || positions.length == 0){
      return res;
    }

    int count  = 0;
    int[] roots = new int[m*n];
    Arrays.fill(roots, -1);

    for(int[] p : positions){
      int root = n*p[0] + p[1];
      roots[root] = root;
      count++;

      for(int[] dir : dirs){
        int x = p[0] + dir[0];
        int y = p[1] + dir[1];
        int nb = x*n + y;
        if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1){
          continue;
        }

        int rootNb = findIsland(roots, nb);
        if(root != rootNb){
          roots[root] = rootNb;
          root = rootNb;
          count--;
        }
      }

      res.add(count);
    }
    return res;
  }

  private int findIsland(int[] roots, int id){
    while(id != roots[id]){
      id = roots[id];
    }
    return id;
  }
}
