package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.GeneralizedAbbreviation;

import java.util.List;

/**
 * Created by Wei on 11/11/16.
 */
public class GeneralizedAbbreviationTest {
  @Test
  public void testcase(){
    GeneralizedAbbreviation ga = new GeneralizedAbbreviation();
    List<String> s = ga.generateAbbreviations("word");
  }
}
