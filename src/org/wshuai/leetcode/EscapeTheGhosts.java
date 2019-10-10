package org.wshuai.leetcode;

/**
 * Created by Wei on 10/1/19.
 * #789 https://leetcode.com/problems/escape-the-ghosts/
 */
public class EscapeTheGhosts {
  // triangle inequality
  public boolean escapeGhosts(int[][] ghosts, int[] target) {
    int distance = Math.abs(target[0]) + Math.abs(target[1]);
    for(int[] g: ghosts){
      int curr = Math.abs(g[0] - target[0]) + Math.abs(g[1] - target[1]);
      if(curr <= distance){
        return false;
      }
    }
    return true;
  }
}
