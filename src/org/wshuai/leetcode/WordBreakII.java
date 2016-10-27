package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 8/29/16.
 * #140 https://leetcode.com/problems/word-break-ii/
 */
public class WordBreakII {
  public static List<String> wordBreak(String s, Set<String> wordDict) {
    List<String> lst = new ArrayList<String>();
    if(s == null || s.isEmpty() || wordDict == null || wordDict.size() == 0){
      return lst;
    }

    int len = s.length();
    boolean[] psb = new boolean[len + 1];
    Arrays.fill(psb, true);
    findWord(0, s, len, wordDict, new StringBuilder(), lst, psb);

    return lst;
  }

  private static void findWord(int eidx, String in, int len, Set<String> wordDict,
    StringBuilder out, List<String> lst, boolean[] psb){
    if(eidx == len){
      lst.add(out.deleteCharAt(out.length() - 1).toString());
      return;
    }else{
      int sidx = eidx;
      eidx++;
      while(eidx <= len){
        String x = in.substring(sidx, eidx);
        if(wordDict.contains(x) && psb[eidx]){
          StringBuilder sb = new StringBuilder(out.toString());
          sb.append(x + " ");
          int size = lst.size();
          findWord(eidx, in, len, wordDict, sb, lst, psb);
          if(lst.size() == size){
            psb[eidx] = false;
          }
        }
        eidx++;
      }
    }
  }
}
