package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 9/19/2016.
 */
public class IntersectionOfTwoArraysII {
  public int[] intersect(int[] nums1, int[] nums2) {
    if(nums1 == null || nums2 == null){
      throw new IllegalArgumentException("Invalid input.");
    }
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    List<Integer> lst = new ArrayList<Integer>();
    int len1 = nums1.length;
    int len2 = nums2.length;
    for(int i = 0; i < len1; i++){
      int k = nums1[i];
      if(map.containsKey(k)){
        int c = map.get(k);
        map.put(k, c + 1);
      }else{
        map.put(k, 1);
      }
    }

    for(int i = 0; i < len2; i++){
      int k = nums2[i];
      if(map.containsKey(k)){
        int c = map.get(k);
        if(c > 0){
          lst.add(k);
          map.put(k, c - 1);
        }
      }
    }

    int len = lst.size();
    int[] r = new int[len];
    for(int i = 0; i < len; i++){
      r[i] = lst.get(i);
    }
    return r;
  }
}
