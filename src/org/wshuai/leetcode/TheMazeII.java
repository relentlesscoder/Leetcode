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
    List<Integer> lst = new ArrayList<Integer>();
    shortestDistanceUtil(maze, start, destination, visited, 0, lst);
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

  private void shortestDistanceUtil(int[][] maze, int[] start, int[] dest, boolean[][] visited, int sum, List<Integer> lst){
    if(start[0] == dest[0] && start[1] == dest[1]){
      lst.add(sum);
      return;
    }
    if(visited[start[0]][start[1]]){
      return;
    }
    visited[start[0]][start[1]] = true;
    int[] up = moveUp(maze, start);
    if(!visited[up[0]][up[1]]){
      int diff = start[0]-up[0];
      shortestDistanceUtil(maze, up, dest, visited, sum+diff, lst);
    }
    int[] down = moveDown(maze, start);
    if(!visited[down[0]][down[1]]){
      int diff = down[0]-start[0];
      shortestDistanceUtil(maze, down, dest, visited, sum+diff, lst);
    }
    int[] left = moveLeft(maze, start);
    if(!visited[left[0]][left[1]]){
      int diff = start[1]-left[1];
      shortestDistanceUtil(maze, left, dest, visited, sum+diff, lst);
    }
    int[] right = moveRight(maze, start);
    if(!visited[right[0]][right[1]]){
      int diff = right[1]-start[1];
      shortestDistanceUtil(maze, right, dest, visited, sum+diff, lst);
    }
    visited[start[0]][start[1]] = false;
    return;
  }

  private int[] moveUp(int[][] maze, int[] curr){
    int[] up = new int[2];
    up[0] = curr[0];
    up[1] = curr[1];
    while(up[0] > 0 && maze[up[0]-1][up[1]] != 1){
      up[0]--;
    }
    return up;
  }

  private int[] moveDown(int[][] maze, int[] curr){
    int rows = maze.length;
    int[] down = new int[2];
    down[0] = curr[0];
    down[1] = curr[1];
    while(down[0] < rows-1 && maze[down[0]+1][down[1]] != 1){
      down[0]++;
    }
    return down;
  }

  private int[] moveLeft(int[][] maze, int[] curr){
    int[] left = new int[2];
    left[0] = curr[0];
    left[1] = curr[1];
    while(left[1] > 0 && maze[left[0]][left[1]-1] != 1){
      left[1]--;
    }
    return left;
  }

  private int[] moveRight(int[][] maze, int[] curr){
    int cols = maze[0].length;
    int[] right = new int[2];
    right[0] = curr[0];
    right[1] = curr[1];
    while(right[1] < cols-1 && maze[right[0]][right[1]+1] != 1){
      right[1]++;
    }
    return right;
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
