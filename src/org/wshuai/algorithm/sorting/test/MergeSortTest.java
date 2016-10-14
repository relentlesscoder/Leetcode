package org.wshuai.algorithm.sorting.test;

import org.junit.Test;
import org.wshuai.utility.ArrayUtil;
import org.wshuai.algorithm.sorting.MergeSort;

import static org.junit.Assert.*;

/**
 * Created by Wei on 8/23/15.
 */
public class MergeSortTest {
  @Test
  public void MergeSortShouldSortInputArray(){
    Integer[] input = new Integer[]{3, 16, 8, 10, 12, 2, 7};
    MergeSort.sort(input);
    assertTrue(ArrayUtil.isSorted(input));
  }

  @Test
  public void MergeSortShouldNotBreakWhenInputIsNull(){
    Comparable[] input = null;
    MergeSort.sort(input);
    assertNull(input);
  }
}
