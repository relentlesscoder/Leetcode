package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Wei on 11/13/16.
 * #452 https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 */
public class MinimumNumberOfArrowsToBurstBalloons {
  //O(n*lg(n)), sort and merge adjacent intervals
  public int findMinArrowShots(int[][] points) {
    if(points == null || points.length == 0){
      return 0;
    }
    int len = points.length;
    Arrays.sort(points, new PointComparator());
    List<int[]> lst = new ArrayList<int[]>();
    lst.add(points[0]);
    for(int i = 1; i < len; i++){
      int[] p = points[i];
      int[] t = lst.get(lst.size()-1);
      if(p[1] < t[0] || p[0] > t[1]){
        lst.add(p);
      }else{
        int[] n = new int[2];
        n[0] = Math.min(p[0], t[0]);
        n[1] = Math.min(p[1], t[1]);
        lst.remove(lst.size()-1);
        lst.add(n);
      }
    }
    return lst.size();
  }
}

class PointComparator implements Comparator<int[]> {
  @Override
  public int compare(int[] x, int[] y){
    return x[0] != y[0] ? x[0]-y[0] : x[1]-y[1];
  }
}
