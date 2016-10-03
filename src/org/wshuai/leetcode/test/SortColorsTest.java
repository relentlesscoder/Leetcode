package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.SortColors;

/**
 * Created by Wei on 9/8/16.
 */
public class SortColorsTest {
  @Test
  public void testcase(){
    int[] nums = new int[]{0, 2};
    SortColors.sortColors(nums);
  }

  @Test
  public void testcase1(){
    int[] nums = new int[]{1, 0};
    SortColors.sortColors(nums);
  }
}
