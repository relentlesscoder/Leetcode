package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.UniqueWordAbbreviation;

/**
 * Created by Wei on 9/19/2016.
 */
public class UniqueWordAbbreviationTest {
  @Test
  public void testcase(){
    String[] strs = new String[]{"a", "a"};
    UniqueWordAbbreviation u = new UniqueWordAbbreviation(strs);
    u.isUnique("a");
  }
}
