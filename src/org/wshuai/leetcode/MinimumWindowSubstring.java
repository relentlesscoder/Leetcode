package org.wshuai.leetcode;

/**
 * Created by Wei on 10/2/2016.
 */
public class MinimumWindowSubstring {
  public String minWindow(String s, String t) {
    if(s == null || t == null){
      return "";
    }
    int sLen = s.length();
    int tLen = t.length();
    if(sLen < tLen){
      return "";
    }

    int minStart = 0;
    int winStart = 0;
    int min = Integer.MAX_VALUE;
    int[] expected = new int[256];
    int[] found = new int[256];
    int count = 0;

    for(int i = 0; i < tLen; i++){
      expected[t.charAt(i)]++;
    }

    for(int winEnd = 0; winEnd < sLen; winEnd++){
      char curr = s.charAt(winEnd);
      if(expected[curr] > 0){
        found[curr]++;
        if(found[curr] <= expected[curr]){
          count++;
        }
      }

      if(count == tLen){
        while(expected[s.charAt(winStart)] == 0
                || found[s.charAt(winStart)] > expected[s.charAt(winStart)]){
          if(expected[s.charAt(winStart)] > 0){
            found[s.charAt(winStart)]--;
          }
          winStart++;
        }

        int winLen = winEnd - winStart + 1;
        if(min > winLen){
          min = winLen;
          minStart = winStart;
        }
      }
    }

    if(min == Integer.MAX_VALUE){
      return "";
    }
    return s.substring(minStart, minStart + min);
  }
}
