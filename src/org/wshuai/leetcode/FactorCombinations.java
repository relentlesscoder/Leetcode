package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/1/2016.
 */
public class FactorCombinations {
  public List<List<Integer>> getFactors(int n) {
    Set<String> set = new HashSet<String>();
    List<List<Integer>> lst = new ArrayList<List<Integer>>();
    List<Integer> ls = new ArrayList<Integer>();
    ls.add(n);
    getFactorsUtil(lst, ls, set);
    return lst;
  }

  private void getFactorsUtil(List<List<Integer>> lst, List<Integer> ls, Set<String> set){
    int len = ls.size();
    int num = ls.get(len - 1);
    if(num <= 2){
      return;
    }
    int dis = (int)Math.sqrt(num);
    for(int i = 2; i <= dis; i++){
      if(num%i == 0){
        int r = num/i;
        if(r > 1){
          List nls = new ArrayList<Integer>(ls);
          nls.set(len - 1, i);
          nls.add(r);
          String key = getKey(nls);
          if(!set.contains(key)){
            set.add(key);
            lst.add(nls);
            getFactorsUtil(lst, nls, set);
          }
        }
      }
    }
  }

  private String getKey(List<Integer> ls){
    Collections.sort(ls);
    StringBuilder sb = new StringBuilder();
    int len = ls.size();
    for(int i=0; i < len; i++){
      sb.append(Integer.toString(ls.get(i)));
      if(i != len - 1){
        sb.append(',');
      }
    }
    return sb.toString();
  }
}
