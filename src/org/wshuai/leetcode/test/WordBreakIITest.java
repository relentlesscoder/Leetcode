package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.WordBreakII;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 8/29/16.
 */
public class WordBreakIITest {
  @Test
  public void testcase(){
    Set<String> st = new HashSet<String>();
    st.add("aaaa");
    st.add("aaa");
    List<String> lst = WordBreakII.wordBreak("aaaaaaa", st);
  }
}
