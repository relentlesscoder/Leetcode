package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.KthLargestElementInAnArray;

/**
 * Created by Wei on 9/12/16.
 */
public class KthLargestElementInAnArrayTest {
  @Test
  public void testcase1(){
    KthLargestElementInAnArray k = new KthLargestElementInAnArray();
    int[] nums = {5,2,4,1,3,6,0};
    int kth = k.findKthLargest(nums, 4);
  }

  @Test
  public void testcase2(){
    KthLargestElementInAnArray k = new KthLargestElementInAnArray();
    int[] nums = {3,1,2,4};
    int kth = k.findKthLargest(nums, 2);
  }
}
