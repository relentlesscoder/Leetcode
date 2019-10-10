package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/29/2019.
 * #1166 https://leetcode.com/problems/design-file-system/
 */
public class DesignFileSystem {
  Map<String, Integer> map;

  public DesignFileSystem() {
    map = new HashMap<>();
    map.put("", -1);
  }

  public boolean createPath(String path, int value) {
    if(map.containsKey(path)){
      return false;
    }
    int i = path.lastIndexOf("/");
    if(!map.containsKey(path.substring(0, i))){
      return false;
    }
    map.put(path, value);
    return true;
  }

  public int get(String path) {
    return map.getOrDefault(path, -1);
  }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */
