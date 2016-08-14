package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.StringToInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/13/2016.
 */
public class StringToIntegerTest {
  @Test
  public void emptyShouldConvertToZero(){
    assertEquals(StringToInteger.myAtoi(""), 0);
  }

  @Test
  public void positiveSignShouldConvertToZero(){
    assertEquals(StringToInteger.myAtoi("+"), 0);
  }

  @Test
  public void negativeSignShouldConvertToZero(){
    assertEquals(StringToInteger.myAtoi("-"), 0);
  }

  @Test
  public void integerGtMaxShouldConvertToMax(){
    assertEquals(StringToInteger.myAtoi("2147483648"), 2147483647);
  }

  @Test
  public void integerGtMaxLongShouldConvertToMax(){
    assertEquals(StringToInteger.myAtoi("9223372036854775809"), 2147483647);
  }

  @Test
  public void integerLtMinShouldConvertToMin(){
    assertEquals(StringToInteger.myAtoi("-2147483649"), -2147483648);
  }

  @Test
  public void validInputWithLeadingSpaceShouldConvert(){
    assertEquals(StringToInteger.myAtoi("     +004500"), 4500);
  }
}
