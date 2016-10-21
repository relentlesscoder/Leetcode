package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/17/16.
 * #47 https://leetcode.com/problems/permutations-ii/
 */
public class PermutationsII {

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if(nums == null || nums.length == 0){
      return res;
    }
    Arrays.sort(nums);
    int len = nums.length;
    boolean[] used = new boolean[len];
    List<Integer> lst = new ArrayList<Integer>();
    permuteUniqueUtil(nums, len, res, used, lst);
    return res;
  }

  private void permuteUniqueUtil(int[] nums, int len, List<List<Integer>> res, boolean[] used, List<Integer> lst){
    if(lst.size() == len){
      List<Integer> temp = new ArrayList<Integer>(lst);
      res.add(temp);
    }else{
      for(int i = 0; i < len; i++){
        if(used[i]){
          continue;
        }
        if(i > 0 && nums[i] == nums[i-1] && !used[i-1]){
          continue;
        }
        used[i] = true;
        lst.add(nums[i]);
        permuteUniqueUtil(nums, len, res, used, lst);
        lst.remove(lst.size()-1);
        used[i] = false;
      }
    }
  }

  public List<List<Integer>> permuteUniqueHashSet(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if(nums == null || nums.length == 0){
      return res;
    }
    int len = nums.length;
    List<Integer> lst = new ArrayList<Integer>();
    Set<List<Integer>> set = new HashSet<List<Integer>>();
    boolean[] visited = new boolean[len];
    permuteUtil(nums, len, lst, visited, res, set);
    return res;
  }

  private void permuteUtil(int[] nums, int len, List<Integer> lst, boolean[] visited, List<List<Integer>> res, Set<List<Integer>> set){
    if(lst.size() == len && !set.contains(lst)){
      List<Integer> temp = new ArrayList<Integer>(lst);
      res.add(temp);
      set.add(lst);
    }else{
      for(int i = 0; i < len; i++){
        if(visited[i]){
          continue;
        }
        visited[i] = true;
        lst.add(nums[i]);
        permuteUtil(nums, len, lst, visited, res, set);
        lst.remove(lst.size()-1);
        visited[i] = false;
      }
    }
  }
}
