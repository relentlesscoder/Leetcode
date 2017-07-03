package org.wshuai.leetcode;

/**
 * Created by Wei on 7/2/17.
 * #248 https://leetcode.com/problems/strobogrammatic-number-iii/
 */
public class StrobogrammaticNumberIII {
  public int strobogrammaticInRange(String low, String high) {
    int[] res = new int[]{0};
    for(int i = low.length(); i <= high.length(); i++){
      strobogrammaticInRangeUtil(low, high, "", i, res);
      strobogrammaticInRangeUtil(low, high, "0", i, res);
      strobogrammaticInRangeUtil(low, high, "1", i, res);
      strobogrammaticInRangeUtil(low, high, "8", i, res);
    }
    return res[0];
  }

  private void strobogrammaticInRangeUtil(String low, String high, String curr, int len, int[] res){
    int cLen = curr.length();
    if(cLen >= len){
      if(cLen != len){
        return;
      }
      if(len != 1 && curr.charAt(0) == '0'){
        return;
      }
      if(len == low.length() && curr.compareTo(low) < 0){
        return;
      }
      if(len == high.length() && curr.compareTo(high) > 0){
        return;
      }
      res[0]++;
      return;
    }
    strobogrammaticInRangeUtil(low, high, "0"+curr+"0", len, res);
    strobogrammaticInRangeUtil(low, high, "1"+curr+"1", len, res);
    strobogrammaticInRangeUtil(low, high, "8"+curr+"8", len, res);
    strobogrammaticInRangeUtil(low, high, "6"+curr+"9", len, res);
    strobogrammaticInRangeUtil(low, high, "9"+curr+"6", len, res);
  }
}
