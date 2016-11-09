package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.FractionToRecurringDecimal;

/**
 * Created by Wei on 11/7/2016.
 */
public class FractionToRecurringDecimalTest {
  @Test
  public void testcase(){
    FractionToRecurringDecimal ft = new FractionToRecurringDecimal();
    String x = ft.fractionToDecimal(-1, -2147483648);
  }
}
