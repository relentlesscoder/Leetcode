package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.NQueens;

import java.util.List;

/**
 * Created by Wei on 10/1/2016.
 */
public class NQueensTest {
  @Test
  public void testcase1(){
    NQueens nq = new NQueens();
    List<List<String>> lst = nq.solveNQueens(4);
  }

  @Test
  public void testcase2(){
    NQueens nq = new NQueens();
    List<List<String>> lst = nq.solveNQueens(5);
  }
}
