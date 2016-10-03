package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/21/2016.
 */
public class BinaryWatch {
  public List<String> readBinaryWatch(int num) {
    List<String> lst = new ArrayList<String>();
    if(num < 0 || num > 8){
      return lst;
    }
    int max = 1 << 10;
    int msk = (1 << 6) - 1;
    for(int i = 0; i < max; i++){
      if(Integer.bitCount(i) == num){
        int hr = i >> 6;
        int mi = i & msk;
        if(hr <= 11 && mi <= 59){
          String val = String.format("%d:%02d", hr, mi);
          lst.add(val);
        }
      }
    }
    return lst;
  }
}
