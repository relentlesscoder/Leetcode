package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ReverseStringII;

/**
 * Created by Wei on 3/13/17.
 */
public class ReverseStringIITest {
  @Test
  public void testcase1(){
    ReverseStringII rs = new ReverseStringII();
    String val = rs.reverseStr("abcd"
      ,3);
  }

  @Test
  public void testcase2(){
    ReverseStringII rs = new ReverseStringII();
    String val = rs.reverseStr("krmyfshbspcgtesxnnljhfursyissjnsocgdhgfxubewllxzqhpasguvlrxtkgatzfybprfmmfithphckksnvjkcvnsqgsgosfxc"
      ,20);
  }
}
