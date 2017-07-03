package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/24/2016.
 * #247 https://leetcode.com/problems/strobogrammatic-number-ii/
 */
public class StrobogrammaticNumberII {
  public List<String> findStrobogrammatic(int n) {
    List<String> lst = new ArrayList<String>();
    if(n < 1){
      return lst;
    }
    boolean odd = (n%2 == 1);
    if(odd){
      n--;
      lst.add("0");
      lst.add("1");
      lst.add("8");
    }else{
      n-=2;
      lst.add("00");
      lst.add("11");
      lst.add("88");
      lst.add("69");
      lst.add("96");
    }
    while(n > 0){
      List<String> temp = new ArrayList<String>();
      for(String s: lst){
        temp.add("0" + s + "0");
        temp.add("1" + s + "1");
        temp.add("8" + s + "8");
        temp.add("6" + s + "9");
        temp.add("9" + s + "6");
      }
      lst = temp;
      n-=2;
    }
    List<String> r = new ArrayList<String>();
    for(String s: lst){
      if(s.length() > 1 && s.charAt(0) == '0'){
        continue;
      }else{
        r.add(s);
      }
    }
    return r;
  }
}
