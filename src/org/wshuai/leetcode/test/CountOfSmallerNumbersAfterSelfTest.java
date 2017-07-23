package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.CountOfSmallerNumbersAfterSelf;

import java.util.List;

/**
 * Created by Wei on 7/23/2017.
 */
public class CountOfSmallerNumbersAfterSelfTest {
  @Test
  public void testcase(){
    CountOfSmallerNumbersAfterSelf cs = new CountOfSmallerNumbersAfterSelf();
    List<Integer> lst = cs.countSmaller(new int[]{5,2,6,1});
  }
}
