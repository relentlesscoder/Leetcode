package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ImplementStrStr;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/16/2016.
 */
public class ImplementStrStrTest {
  @Test
  public void testKMP(){
    int[] arr = ImplementStrStr.computeLspTable("AAACAAAA");
  }

  @Test
  public void testBuildKMP(){
    int[] arr = ImplementStrStr.buildKMPTable("abababca");
  }
}
