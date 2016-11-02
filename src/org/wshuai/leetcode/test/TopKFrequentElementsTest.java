package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.TopKFrequentElements;

import java.util.List;

/**
 * Created by Wei on 11/2/2016.
 */
public class TopKFrequentElementsTest {
  @Test
  public void testcase(){
    TopKFrequentElements tk = new TopKFrequentElements();
    List<Integer> l = tk.topKFrequent(new int[]{1, 2},2);
  }
}
