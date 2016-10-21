package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.RotateImage;

/**
 * Created by Wei on 10/21/2016.
 */
public class RotateImageTest {
  @Test
  public void testcase(){
    RotateImage ri = new RotateImage();
    int[][] matrix = new int[][]{{1, 2}, {3,4}};
    ri.rotate(matrix);
  }
}
