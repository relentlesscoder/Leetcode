package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 8/3/17.
 * #635 https://leetcode.com/problems/design-log-storage-system/
 */
public class DesignLogStorageSystem {
  List<String[]> timestamps = new LinkedList<String[]>();
  List<String> units = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
  int[] indices = new int[]{4,7,10,13,16,19};

  public DesignLogStorageSystem() {

  }

  public void put(int id, String timestamp) {
    timestamps.add(new String[]{Integer.toString(id), timestamp});
  }

  public List<Integer> retrieve(String s, String e, String gra) {
    List<Integer> res = new LinkedList<Integer>();
    int idx = indices[units.indexOf(gra)];
    for (String[] timestamp : timestamps) {
      if (timestamp[1].substring(0, idx).compareTo(s.substring(0, idx)) >= 0 &&
              timestamp[1].substring(0, idx).compareTo(e.substring(0, idx)) <= 0) res.add(Integer.parseInt(timestamp[0]));
    }
    return res;
  }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
