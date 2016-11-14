package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.RestoreIPAddresses;

import java.util.List;

/**
 * Created by Wei on 11/10/2016.
 */
public class RestoreIPAddressesTest {
  @Test
  public void testcase(){
    RestoreIPAddresses ri = new RestoreIPAddresses();
    List<String> x = ri.restoreIpAddresses("010010");
  }
}
