package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.algorithm.stringMatching.KMP;
import org.wshuai.leetcode.ImplementStrStr;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/16/2016.
 */
public class ImplementStrStrTest {
/*  @Test
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
  }*/

  @Test
  public void testKMP1(){
    KMP kmp = new KMP();
    int x = kmp.kmpMatch("ABABDABACDABABCABAB", "ABABCABAB");
  }

  @Test
  public void testKMP2() {
    KMP kmp = new KMP();
    int x = kmp.kmpMatch("mississippi", "issip");
  }

}
