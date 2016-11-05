package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.PaintHouseII;

/**
 * Created by Wei on 11/4/16.
 */
public class PaintHouseIITest {
  @Test
  public void testcase(){
    PaintHouseII ph = new PaintHouseII();
    int[][] matrix = new int[][]{
      {1,5,3},
      {2,9,4}
    };
    int x = ph.minCostII(matrix);
  }
}
