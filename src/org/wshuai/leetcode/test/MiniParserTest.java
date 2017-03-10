package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MiniParser;
import org.wshuai.leetcode.NestedInteger;

/**
 * Created by Wei on 3/10/17.
 */
public class MiniParserTest {
  @Test
  public void testcase(){
    MiniParser mp = new MiniParser();
    NestedInteger ni = mp.deserialize("[123,456,[788,799,833],[[]],10,[]]");
  }
}
