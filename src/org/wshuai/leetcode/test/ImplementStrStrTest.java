package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ImplementStrStr;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/16/2016.
 */
public class ImplementStrStrTest {
  @Test
  public void testBuildKMP1(){
    char[] pattern = "AAACAAAA".toCharArray();
    int[] lsp = new int[pattern.length];
    ImplementStrStr.buildLSP(pattern, lsp);
  }

  @Test
  public void testBuildKMP2(){
    char[] pattern = "abababca".toCharArray();
    int[] lsp = new int[pattern.length];
    ImplementStrStr.buildLSP(pattern, lsp);
  }

  @Test
  public void testKMP1(){
    int x = ImplementStrStr.strStrKMP("ABABDABACDABABCABAB", "ABABCABAB");
  }

  @Test
  public void testKMP2() {
    int x = ImplementStrStr.strStrKMP("mississippi", "issip");
  }

}
