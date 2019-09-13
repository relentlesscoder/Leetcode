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

  @Test
  public void testcase1(){
    String s = "word";
    String s1 = s.substring(0,0);
    String s2 = s.substring(4,4);
  }
}
