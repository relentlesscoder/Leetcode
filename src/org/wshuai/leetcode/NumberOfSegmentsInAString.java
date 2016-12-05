package org.wshuai.leetcode;

/**
 * Created by Wei on 12/4/16.
 * #434 https://leetcode.com/problems/number-of-segments-in-a-string/
 */
public class NumberOfSegmentsInAString {
  //O(n)
  public int countSegments(String s) {
    if(s == null || s.isEmpty()){
      return 0;
    }
    int count = 0;
    int len = s.length();
    for(int i = 0; i < len; i++){
      if(s.charAt(i) == ' '){
        continue;
      }
      count++;
      while(i < len && s.charAt(i) != ' '){
        i++;
      }
    }
    return count;
  }
}
