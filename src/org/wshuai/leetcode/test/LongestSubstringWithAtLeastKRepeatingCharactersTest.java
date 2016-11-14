package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LongestSubstringWithAtLeastKRepeatingCharacters;

/**
 * Created by Wei on 11/10/16.
 */
public class LongestSubstringWithAtLeastKRepeatingCharactersTest {
  @Test
  public void testcase(){
    LongestSubstringWithAtLeastKRepeatingCharacters ls =
      new LongestSubstringWithAtLeastKRepeatingCharacters();
    int max = ls.longestSubstring("aaabb",3);
  }
}
