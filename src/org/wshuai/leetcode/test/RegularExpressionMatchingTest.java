package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.RegularExpressionMatching;

/**
 * Created by Wei on 11/1/16.
 */
public class RegularExpressionMatchingTest {
  @Test
  public void testcase(){
    RegularExpressionMatching re = new RegularExpressionMatching();
    boolean r = re.isMatchDP("aab","c*a*b");
  }
}
