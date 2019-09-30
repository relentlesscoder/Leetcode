package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 9/29/2019.
 * #1207 https://leetcode.com/problems/unique-number-of-occurrences/
 */
public class UniqueNumberOfOccurrences {
  public boolean uniqueOccurrences(int[] arr) {
    Map<Integer, Integer> count = new HashMap<>();
    for(int v: arr){
      count.put(v, count.getOrDefault(v, 0) + 1);
    }
    Set<Integer> set = new HashSet<>();
    for(int c: count.values()){
      if(!set.add(c)){
        return false;
      }
    }
    return true;
  }
}
