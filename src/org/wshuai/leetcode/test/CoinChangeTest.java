package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.CoinChange;

/**
 * Created by Wei on 11/7/16.
 */
public class CoinChangeTest {
  @Test
  public void testcase(){
    CoinChange cc = new CoinChange();
    int count = cc.coinChange(new int[]{1,2147483647}, 2);
  }
}
