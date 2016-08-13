package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ZigZagConversion;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/12/2016.
 */
public class ZigZagConversionTest {
  @Test
  public void validInputShouldReturnZigZagOutput(){
    String s = ZigZagConversion.convert("ABC", 2);
    assertEquals(s, "ACB");
  }

  @Test
  public void rowNumGreaterThanInputStringLengthShouldReturnZigZagOutput(){
    String s = ZigZagConversion.convert("AB", 3);
    assertEquals(s, "AB");
  }
}
