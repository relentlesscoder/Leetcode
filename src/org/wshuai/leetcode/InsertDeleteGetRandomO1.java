package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 3/27/2017.
 * #380 https://leetcode.com/problems/insert-delete-getrandom-o1/
 */
public class InsertDeleteGetRandomO1 {
  private Map<Integer, Integer> map;
  private List<Integer> lst;
  private Random rand;

  /** Initialize your data structure here. */
  public InsertDeleteGetRandomO1() {
    map = new HashMap<Integer, Integer>();
    lst = new ArrayList<Integer>();
    rand = new Random();
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if(map.containsKey(val)){
      return false;
    }
    lst.add(val);
    map.put(val, lst.size()-1);
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if(map.containsKey(val)){
      int idx = map.get(val);
      if(idx != lst.size()-1){
        int last = lst.get(lst.size()-1);
        lst.set(idx, last);
        map.put(last, idx);
      }
      lst.remove(lst.size()-1);
      map.remove(val);
      return true;
    }
    return false;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    return lst.get(rand.nextInt(lst.size()));
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
