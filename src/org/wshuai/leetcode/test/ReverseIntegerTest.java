package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ReverseInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/12/2016.
 */
public class ReverseIntegerTest {
  @Test
  public void validInputShouldReturnReverse(){
    int x = ReverseInteger.reverse(123);
    assertEquals(x, 321);
  }

  @Test
  public void negativeInputShouldReturnReverse(){
    int x = ReverseInteger.reverse(-2147483648);
    assertEquals(x, 0);
  }
}
