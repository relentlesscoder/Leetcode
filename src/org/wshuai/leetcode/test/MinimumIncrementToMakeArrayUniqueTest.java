package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MinimumIncrementToMakeArrayUnique;

public class MinimumIncrementToMakeArrayUniqueTest {

  @Test
  public void testcase(){
    MinimumIncrementToMakeArrayUnique mia = new MinimumIncrementToMakeArrayUnique();
    int res = mia.minIncrementForUnique(new int[]{3,2,1,2,1,7});
  }
}
