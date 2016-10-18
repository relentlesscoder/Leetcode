package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/1/2016.
 */
public class FactorCombinations {

  public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> lst = new ArrayList<Integer>();
    getFactorsUtil(n, 2, res, lst);
    return res;
  }

  private void getFactorsUtil(int n, int start, List<List<Integer>> res, List<Integer> lst){
    if(n == 1){
      if(lst.size() > 1){
        res.add(new ArrayList<Integer>(lst));
      }
      return;
    }else{
      for(int i = start; i <= Math.sqrt(n); i++){
        if(n%i == 0){
          lst.add(i);
          getFactorsUtil(n/i, i, res, lst);
          lst.remove(lst.size()-1);
        }
      }
    }

    lst.add(n);
    getFactorsUtil(1, n, res, lst);
    lst.remove(lst.size()-1);
  }

  public List<List<Integer>> getFactorsBT(int n) {
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
