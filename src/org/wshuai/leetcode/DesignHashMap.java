package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 9/18/2019.
 * #706 https://leetcode.com/problems/design-hashmap/
 */
public class DesignHashMap {
  private int[] arr;

  /** Initialize your data structure here. */
  public DesignHashMap() {
    arr = new int[10_000_001];
    Arrays.fill(arr, -1);
  }

  /** value will always be non-negative. */
  public void put(int key, int value) {
    arr[key] = value;
  }

  /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
  public int get(int key) {
    return arr[key];
  }

  /** Removes the mapping of the specified value key if this map contains a mapping for the key */
  public void remove(int key) {
    arr[key] = -1;
  }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
