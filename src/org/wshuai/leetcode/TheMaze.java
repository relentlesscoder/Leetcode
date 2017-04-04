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
    return hasPathUtil(maze, start, destination, visited);
  }

  private boolean hasPathUtil(int[][] maze, int[] start, int[] dest, boolean[][] visited){
    if(start[0] == dest[0] && start[1] == dest[1]){
      return true;
    }
    if(visited[start[0]][start[1]]){
      return false;
    }
    visited[start[0]][start[1]] = true;
    int[] up = moveUp(maze, start);
    if(!visited[up[0]][up[1]] && hasPathUtil(maze, up, dest, visited)){
      return true;
    }
    int[] down = moveDown(maze, start);
    if(!visited[down[0]][down[1]] && hasPathUtil(maze, down, dest, visited)){
      return true;
    }
    int[] left = moveLeft(maze, start);
    if(!visited[left[0]][left[1]] && hasPathUtil(maze, left, dest, visited)){
      return true;
    }
    int[] right = moveRight(maze, start);
    if(!visited[right[0]][right[1]] && hasPathUtil(maze, right, dest, visited)){
      return true;
    }
    return false;
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
