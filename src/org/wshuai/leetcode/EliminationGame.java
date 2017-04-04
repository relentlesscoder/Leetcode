package org.wshuai.leetcode;

/**
 * Created by Wei on 4/3/17.
 * #390 https://leetcode.com/problems/elimination-game/
 */
public class EliminationGame {
  //See http://blog.csdn.net/mebiuw/article/details/52713918
  public int lastRemaining(int n) {
    return n==1?1:2*(n/2 + 1 - lastRemaining(n/2));
  }
}
