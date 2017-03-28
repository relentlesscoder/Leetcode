package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.GraphValidTree;

/**
 * Created by Wei on 3/14/2017.
 */
public class GraphValidTreeTest {
  @Test
  public void testcase(){
    GraphValidTree gv = new GraphValidTree();
    boolean res = gv.validTree(5, new int[][]{
            {0,1},{0,2},{2,3},{2,4}
    });
  }
}
