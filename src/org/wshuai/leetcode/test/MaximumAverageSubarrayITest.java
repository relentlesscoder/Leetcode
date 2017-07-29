package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MaximumAverageSubarrayI;

/**
 * Created by Wei on 7/28/17.
 */
public class MaximumAverageSubarrayITest {
  @Test
  public void testcase(){
    MaximumAverageSubarrayI ma = new MaximumAverageSubarrayI();
    double max = ma.findMaxAverage(new int[]{-1}, 1);
  }
}
