package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.SubstringWithSoncatenationOfAllWords;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/17/2016.
 */
public class SubstringWithSoncatenationOfAllWordsTest {
  @Test
  public void test1(){
    SubstringWithSoncatenationOfAllWords.findSubstringBrutalForce("barfoothefoobarman", new String[]{"foo","bar"});
  }
}
