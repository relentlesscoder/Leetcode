package org.wshuai.leetcode;

/**
 * Created by Wei on 7/23/17.
 * #598 https://leetcode.com/problems/range-addition-ii/
 */
public class RangeAdditionII {
  public int maxCount(int m, int n, int[][] ops) {
    if(ops == null || ops.length == 0){
      return m*n;
    }
    for(int[] op: ops){
      m = Math.min(m, op[0]);
      n = Math.min(n, op[1]);
    }
    return m*n;
  }
}
