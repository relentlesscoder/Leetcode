package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.DiagonalTraverse;

/**
 * Created by Wei on 2/19/17.
 */
public class DiagonalTraverseTest {
  @Test
  public void test(){
    int[][] matrix = new int[][]{
      {1,2,3},{4,5,6},{7,8,9}
    };
    DiagonalTraverse dt = new DiagonalTraverse();
    int[] res = dt.findDiagonalOrder(matrix);
  }

  @Test
  public void test1(){
    int[][] matrix = new int[][]{
      {1,2,3}
    };
    DiagonalTraverse dt = new DiagonalTraverse();
    int[] res = dt.findDiagonalOrder(matrix);
  }
}
