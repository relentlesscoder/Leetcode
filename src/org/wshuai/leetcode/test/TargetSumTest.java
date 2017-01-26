package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.TargetSum;

/**
 * Created by Wei on 1/25/17.
 */
public class TargetSumTest {
  @Test
  public void testcase(){
    TargetSum ts = new TargetSum();
    int x = ts.findTargetSumWaysRecursive(new int[]{7,7,17,1,46,38,8,32,35,18,43,48,9,17,6,6,42,10,2,32}, 38);
  }

  @Test
  public void testcase1(){
    TargetSum ts = new TargetSum();
    int x = ts.findTargetSumWaysDFS(new int[]{7,7,17,1,46,38,8,32,35,18,43,48,9,17,6,6,42,10,2,32}, 38);
  }
}
