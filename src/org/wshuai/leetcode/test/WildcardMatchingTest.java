package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.WildcardMatching;

/**
 * Created by Wei on 8/30/2016.
 */
public class WildcardMatchingTest {
  @Test
  public void testcase(){
    boolean match = WildcardMatching.isMatchRecursive("ho", "**ho");
  }

  @Test
  public void timeLimitTest1(){
    String s = "abbaabaaabbabbaaba";
    String p = "*aa*ba*a*bb*aa*";
    boolean match = WildcardMatching.isMatchRecursive(s, p);
  }

  @Test
  public void timeLimitTest2(){
    String s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
    String p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
    boolean match = WildcardMatching.isMatchRecursive(s, p);
  }

  @Test
  public void testDP(){
    String s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
    String p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
    boolean match = WildcardMatching.isMatchDP(s, p);
  }

  @Test
  public void testDP2(){
    boolean match = WildcardMatching.isMatchDP2("aa", "aa");
  }

}
