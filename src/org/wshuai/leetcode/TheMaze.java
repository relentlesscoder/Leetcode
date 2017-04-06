package org.wshuai.leetcode;

/**
 * Created by Wei on 4/4/2017.
 * #490 https://leetcode.com/problems/the-maze/
 */
public class TheMaze {
  //DFS
  public boolean hasPath(int[][] maze, int[] start, int[] destination) {
    int rows = maze.length;
    int cols = maze[0].length;
    boolean[][] visited = new boolean[rows][cols];
    int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    return hasPathUtil(maze, start, destination, visited, dir, rows, cols);
  }

  private boolean hasPathUtil(int[][] maze, int[] start, int[] dest, boolean[][] visited,
                              int[][] dir, int rows, int cols){
    if(start[0] == dest[0] && start[1] == dest[1]){
      return true;
    }
    if(visited[start[0]][start[1]]){
      return false;
    }
    visited[start[0]][start[1]] = true;
    for(int i = 0; i < 4; i++){
      int x = start[0];
      int y = start[1];
      while(x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 0){
        x += dir[i][0];
        y += dir[i][1];
      }
      x -= dir[i][0];
      y -= dir[i][1];
      if(!visited[x][y] && hasPathUtil(maze, new int[]{x, y}, dest, visited, dir, rows, cols)){
        return true;
      }
    }
    return false;
  }
}
