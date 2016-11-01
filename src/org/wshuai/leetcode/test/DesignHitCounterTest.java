package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.DesignHitCounter;

/**
 * Created by Wei on 10/31/16.
 */
public class DesignHitCounterTest {
  @Test
  public void testcase(){
    DesignHitCounter dh = new DesignHitCounter();
    dh.hit(1);
    dh.hit(2);
    dh.hit(3);
    dh.hit(4);
    dh.hit(300);
    dh.hit(300);
    dh.hit(301);
  }
}
