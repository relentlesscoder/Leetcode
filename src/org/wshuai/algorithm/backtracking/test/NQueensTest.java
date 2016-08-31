package org.wshuai.algorithm.backtracking.test;

import org.junit.Test;
import org.wshuai.algorithm.backtracking.NQueens;

/**
 * Created by Wei on 8/28/16.
 */
public class NQueensTest {
  @Test
  public void testcase(){
    NQueens nQueens = new NQueens(4);
    nQueens.solve(4);
  }
}
