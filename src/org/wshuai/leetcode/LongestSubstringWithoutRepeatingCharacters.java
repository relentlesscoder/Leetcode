package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 8/9/2015.
 * #3 https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {
  public static int lengthOfLongestSubstring(String s) {
    int result = 0;
    int startIndex = 0;
    if (s != null && s != "") {
      Map<Character, Integer> map = new HashMap<Character, Integer>();
      for (int i = 0; i < s.length(); i++) {
        char character = s.charAt(i);
        Integer index = map.get(character);
        if (index != null && index >= startIndex) {
          int currentLength = i - startIndex;
          result = result < currentLength ? currentLength : result;
          startIndex = index + 1;
        }
        else if(i == s.length() - 1){
          int currentLength = i - startIndex + 1;
          result = result < currentLength ? currentLength : result;
        }
        map.put(character, i);
      }
    }

    return result;
  }
}
