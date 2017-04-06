package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 4/4/2017.
 * #505 https://leetcode.com/problems/the-maze-ii/
 */
public class TheMazeII {
  
  //BFS, see https://discuss.leetcode.com/topic/77472/similar-to-the-maze-easy-understanding-java-bfs-solution
  public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    int rows = maze.length;
    int cols = maze[0].length;
    int[][] length = new int[rows][cols];
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        length[i][j] = Integer.MAX_VALUE;
      }
    }
    int[][] dir = new int[][]{{-1, 0},{0, 1},{1, 0},{0, -1}};
    PriorityQueue<MazeCell> queue = new PriorityQueue<MazeCell>(new MazeCellComparator());
    queue.offer(new MazeCell(start[0], start[1], 0));
    while(!queue.isEmpty()){
      MazeCell cell = queue.poll();
      if(length[cell.row][cell.col] <= cell.len){
        continue;
      }
      length[cell.row][cell.col] = cell.len;
      for(int i = 0; i < 4; i++){
        int x = cell.row;
        int y = cell.col;
        int l = cell.len;
        while(x >= 0 && y >= 0 && x < rows && y < cols && maze[x][y] == 0){
          x += dir[i][0];
          y += dir[i][1];
          l++;
        }
        x -= dir[i][0];
        y -= dir[i][1];
        l--;
        queue.offer(new MazeCell(x, y, l));
      }
    }
    return length[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : length[destination[0]][destination[1]];
  }

  //TLE
  public int shortestDistanceDFS(int[][] maze, int[] start, int[] destination) {
    int rows = maze.length;
    int cols = maze[0].length;
    boolean[][] visited = new boolean[rows][cols];
    int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    List<Integer> lst = new ArrayList<Integer>();
    shortestDistanceUtil(maze, start, destination, visited, 0, lst, dir, rows, cols);
    if(lst.size() == 0){
      return -1;
    }else{
      int min = Integer.MAX_VALUE;
      for(int val: lst){
        min = Math.min(min, val);
      }
      return min;
    }
  }

  private void shortestDistanceUtil(int[][] maze, int[] start, int[] dest, boolean[][] visited,
                                    int sum, List<Integer> lst, int[][] dir, int rows, int cols){
    if(start[0] == dest[0] && start[1] == dest[1]){
      lst.add(sum);
      return;
    }
    if(visited[start[0]][start[1]]){
      return;
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
      if(!visited[x][y]){
        int diff = Math.max(Math.abs(x-start[0]), Math.abs(y-start[1]));
        shortestDistanceUtil(maze, new int[]{x, y}, dest, visited, sum+diff, lst, dir, rows, cols);
      }
    }
    visited[start[0]][start[1]] = false;
    return;
  }
}

class MazeCellComparator implements Comparator<MazeCell> {
  @Override
  public int compare(MazeCell c1, MazeCell c2){
    return c1.len - c2.len;
  }
}

class MazeCell{
  int row;
  int col;
  int len;

  public MazeCell(int r, int c, int l){
    row = r;
    col = c;
    len = l;
  }
}
