package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 11/7/2016.
 * #356 https://leetcode.com/problems/line-reflection/
 */
public class LineReflection {
  public boolean isReflected(int[][] points) {
    if(points == null || points.length == 0){
      return true;
    }
    int len = points.length;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
    for(int i = 0; i < len; i++){
      int val = points[i][0];
      min = val < min ? val : min;
      max = val > max ? val : max;
      if(map.containsKey(val)){
        Set<Integer> set = map.get(val);
        set.add(points[i][1]);
      }else{
        Set<Integer> set = new HashSet<Integer>();
        set.add(points[i][1]);
        map.put(val, set);
      }
    }
    double mid = 1.0*(max+min)/2;
    for(int i = 0; i < len; i++){
      int xVal = points[i][0];
      int key = (int)(2*mid-xVal);
      if(map.containsKey(key) && map.get(key).contains(points[i][1])){
        continue;
      }else{
        return false;
      }
    }
    return true;
  }
}
