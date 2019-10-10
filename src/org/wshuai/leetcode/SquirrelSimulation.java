package org.wshuai.leetcode;

/**
 * Created by Wei on 10/1/19.
 * #573 https://leetcode.com/problems/squirrel-simulation/
 */
public class SquirrelSimulation {
  public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
    int min = Integer.MAX_VALUE;
    int sum = 0;
    int[] distance = new int[nuts.length];
    for(int i = 0; i < nuts.length; i++){
      int dist = getDistance(nuts[i], tree);
      sum += 2 * dist;
      distance[i] = dist;
    }
    for(int i = 0; i < nuts.length; i++){
      int local = sum;
      local += getDistance(squirrel, nuts[i]);
      local -= distance[i];
      min = Math.min(local, min);
    }
    return min;
  }

  private int getDistance(int[] p1, int[] p2){
    return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
  }
}
