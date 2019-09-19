package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ImplementMagicDictionary;

public class ImplementMagicDictionaryTest {
  @Test
  public void testcase1(){
    ImplementMagicDictionary md = new ImplementMagicDictionary();
    md.buildDict(new String[]{"hello", "leetcode"});
    boolean b1 = md.search("hello");
    boolean b2 = md.search("hhllo");
    boolean b3 = md.search("hell");
    boolean b4 = md.search("leetcoded");
  }

  @Test
  public void testcase2(){
    ImplementMagicDictionary md = new ImplementMagicDictionary();
    md.buildDict(new String[]{"a","b","ab","abc","abcabacbababdbadbfaejfoiawfjaojfaojefaowjfoawjfoawj",
      "abcdefghijawefe","aefawoifjowajfowafjeoawjfaow","cba","cas","aaewfawi","babcda","bcd","awefj"});
    boolean b1 = md.search("abc");
    boolean b2 = md.search("cba");
  }
}
