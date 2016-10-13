package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MaxPointsOnALine;
import org.wshuai.leetcode.Point;

/**
 * Created by Wei on 10/3/16.
 */
public class MaxPointsOnALineTest {
  @Test
  public void testcase(){
    MaxPointsOnALine mp = new MaxPointsOnALine();
    Point[] ps = new Point[3];
    ps[0] = new Point(2,3);
    ps[1] = new Point(3,3);
    ps[2] = new Point(-5,3);
    int max = mp.maxPoints(ps);
  }
}
