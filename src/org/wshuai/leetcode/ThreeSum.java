package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 8/14/2016.
 */
public class ThreeSum {

  // Time complexity n^2
  public static List<List<Integer>> threeSumHashMap(int[] nums) {
    if(nums == null){
      throw new IllegalArgumentException("Invalid input.");
    }
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    int len = nums.length;
    if(len < 3){
      return result;
    }
    Map<Integer, List<int[]>> hmap = new HashMap<Integer, List<int[]>>();
    for(int i = 0; i < len - 1; i++){
      for(int j = i + 1; j < len; j++){
        int[] arr = new int[2];
        arr[0] = i;
        arr[1] = j;
        int sum = nums[i] + nums[j];
        if(hmap.containsKey(sum)){
          List<int[]> lst = hmap.get(sum);
          lst.add(arr);
        }else{
          List<int[]> lst = new ArrayList<int[]>();
          lst.add(arr);
          hmap.put(sum, lst);
        }
      }
    }
    for(int x = 0; x < len; x++){
      int diff = 0 - nums[x];
      if(hmap.containsKey(diff)){
        List<int[]> lstPos = hmap.get(diff);
        for(int[] arrPos : lstPos){
          if(x == arrPos[0] || x == arrPos[1]){
            continue;
          }

          int first = nums[arrPos[0]];
          int second = nums[arrPos[1]];
          List<Integer> l = new ArrayList<Integer>();
          int minij = first < second ? first : second;
          int maxij = first >= second ? first : second;
          if(nums[x] < minij){
            l.add(nums[x]);
            l.add(minij);
            l.add(maxij);
          }else if(nums[x] >= minij && nums[x] <= maxij){
            l.add(minij);
            l.add(nums[x]);
            l.add(maxij);
          }else{
            l.add(minij);
            l.add(maxij);
            l.add(nums[x]);
          }
          boolean added = false;
          for(List<Integer> lst : result){
            if(lst.get(0) == l.get(0) && lst.get(1) == l.get(1) && lst.get(2) == l.get(2)){
              added = true;
              break;
            }
          }
          if(!added){
            result.add(l);
          }
        }
      }
    }

    return result;
  }

  // Time complexity n^3
  public static List<List<Integer>> threeSumBrutalForce(int[] nums) {
    if(nums == null){
      throw new IllegalArgumentException("Invalid input.");
    }
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    int len = nums.length;
    if(len < 3){
      return result;
    }
    for(int i = 0; i < len - 2; i++){
      for(int j = i+1; j < len - 1; j++){
        for(int x = j+1; x < len; x++){
          if(nums[i] + nums[j] + nums[x] == 0){
            List<Integer> l = new ArrayList<Integer>();
            int minij = nums[i] < nums[j] ? nums[i] : nums[j];
            int maxij = nums[i] >= nums[j] ? nums[i] : nums[j];
            if(nums[x] < minij){
              l.add(nums[x]);
              l.add(minij);
              l.add(maxij);
            }else if(nums[x] >= minij && nums[x] <= maxij){
              l.add(minij);
              l.add(nums[x]);
              l.add(maxij);
            }else{
              l.add(minij);
              l.add(maxij);
              l.add(nums[x]);
            }
            boolean added = false;
            for(List<Integer> lst : result){
              if(lst.get(0) == l.get(0) && lst.get(1) == l.get(1) && lst.get(2) == l.get(2)){
                added = true;
                break;
              }
            }
            if(!added){
              result.add(l);
            }
          }
        }
      }
    }

    return result;
  }
}
