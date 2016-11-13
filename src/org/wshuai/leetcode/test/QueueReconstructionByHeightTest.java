package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.QueueReconstructionByHeight;

/**
 * Created by Wei on 11/12/16.
 */
public class QueueReconstructionByHeightTest {
  @Test
  public void testcase(){
    QueueReconstructionByHeight qr = new QueueReconstructionByHeight();
    int[][] people = new int[][]{
      {7,0},{4,4},{7,1},{5,0},{6,1},{5,2}
    };
    int[][] res = qr.reconstructQueue(people);
  }
}
