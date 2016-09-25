package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LongestAbsoluteFilePath;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 9/21/2016.
 */
public class LongestAbsoluteFilePathTest {
  @Test
  public void testcase(){
    LongestAbsoluteFilePath la = new LongestAbsoluteFilePath();
    int max = la.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext");
    assertEquals(32, max);
  }

  @Test
  public void testcase1(){
    LongestAbsoluteFilePath la = new LongestAbsoluteFilePath();
    int max = la.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
    assertEquals(20, max);
  }

  @Test
  public void testcase2(){
    LongestAbsoluteFilePath la = new LongestAbsoluteFilePath();
    int max = la.lengthLongestPath("a.txt");
    assertEquals(5, max);
  }
}
