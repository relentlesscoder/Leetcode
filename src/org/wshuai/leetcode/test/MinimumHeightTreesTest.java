package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MinimumHeightTrees;

import java.util.List;

/**
 * Created by Wei on 3/20/17.
 */
public class MinimumHeightTreesTest {
  @Test
  public void testcase(){
    MinimumHeightTrees mh = new MinimumHeightTrees();
    List<Integer> nodes = mh.findMinHeightTrees(6,
      new int[][]{{3,0},{3,1},{3,2},{3,4},{5,4}});
  }
}
