package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LongestSubstringWithoutRepeatingCharacters;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Wei on 8/9/2015.
 */
public class LongestSubstringWithoutRepeatingCharactersTest {
  @Test
  public void lengthOfLongestSubstringShouldReturnCorrectLength() {
    String s1 = "abcabcbb";
    String s2 = "bbbbb";

    assertEquals(3, LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s1));
    assertEquals(1, LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s2));
  }

  @Test
  public void lengthOfLongestSubstringShouldReturnCorrectLengthWithNoRepeatingCharacters() {
    String s1 = "abcdefg";
    String s2 = "a";

    assertEquals(7, LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s1));
    assertEquals(1, LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s2));
  }
}
