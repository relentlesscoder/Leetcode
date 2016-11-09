package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/7/2016.
 * #166 https://leetcode.com/problems/fraction-to-recurring-decimal/
 */
public class FractionToRecurringDecimal {
  public String fractionToDecimal(int numerator, int denominator) {
    if(numerator == 0 || denominator == 0){
      return "0";
    }
    long num = numerator;
    long den = denominator;
    Map<Long, Integer> map = new HashMap<Long, Integer>();
    boolean neg = (num > 0 && den < 0) || (num < 0 && den > 0);
    num = Math.abs(num);
    den = Math.abs(den);
    String val = (neg ? "-" : "") + Long.toString(num/den);
    if(num%den != 0){
      StringBuilder frac = new StringBuilder();
      num = num%den;
      int cnt = 0;
      map.put(num, cnt);
      while(num != 0){
        num *= 10;
        frac.append(num/den);
        num = num%den;
        if(map.containsKey(num)){
          int pos = map.get(num);
          String pre = frac.substring(0, pos);
          String post = frac.substring(pos);
          return val + "." + pre + "(" + post + ")";
        }
        cnt++;
        map.put(num, cnt);
      }
      return val + "." + frac.toString();
    }
    return val;
  }
}
