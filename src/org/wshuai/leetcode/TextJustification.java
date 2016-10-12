package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/11/2016.
 * #68 https://leetcode.com/problems/text-justification/
 */
public class TextJustification {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> lst = new ArrayList<String>();
    if(words == null || words.length == 0){
      return lst;
    }

    int len = words.length;
    int s = 0;
    int i = 0;
    int cLen = 0;
    while(i < len){
      int wLen = words[i].length();
      cLen += wLen;
      if(cLen + (i-s) <= maxWidth){
        if(i == len - 1){
          String line = createLine(words, maxWidth, s, i, true);
          lst.add(line);
          break;
        }
        i++;
      }else{
        String line = createLine(words, maxWidth, s, i-1, false);
        lst.add(line);
        cLen = 0;
        s = i;
      }
    }

    return lst;
  }

  private String createLine(String[] words, int max, int s, int e, boolean last){
    StringBuilder sb = new StringBuilder();

    int count = e-s;
    for(int i = s; i <= e; i++){
      max -= words[i].length();
    }
    // only one word
    if(count == 0 || last){
      for(int i = s; i <= e; i++){
        sb.append(words[i]);
        if(max > 0){
          sb.append(" ");
          max--;
        }
      }
      while(max > 0){
        sb.append(" ");
        max--;
      }
    }else{
      int space = max/count;
      int offset = max%count;
      for(int i = s; i <= e; i++){
        sb.append(words[i]);
        if(i != e){
          int sp = space;
          while(sp > 0){
            sb.append(" ");
            sp--;
          }
          if(offset > 0){
            sb.append(" ");
            offset--;
          }
        }
      }
    }

    return sb.toString();
  }
}
