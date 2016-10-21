package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/3/16.
 * #149 https://leetcode.com/problems/max-points-on-a-line/
 */
public class MaxPointsOnALine {
  public int maxPoints(Point[] points){
    if(points == null){
      return 0;
    }
    if(points.length <= 2){
      return points.length;
    }
    int max = 2;
    int len = points.length;
    Map<Double, Integer> map = new HashMap<Double, Integer>();
    for(int i = 0; i< len-1; i++){
      Point p1 = points[i];
      int same = 0;
      int vertical = 0;
      for(int j = i+1; j < len; j++){
        Point p2 = points[j];
        if(p1.x == p2.x){
          if(p1.y == p2.y){
            same++;
          }else{
            vertical++;
          }
        }else{
          Double slope = 1.0*(p2.y - p1.y)/(p2.x - p1.x);
          slope = slope == -0.0 ? 0.0 : slope;
          if(map.containsKey(slope)){
            int count = map.get(slope);
            map.put(slope, count+1);
          }else{
            map.put(slope, 1);
          }
        }
      }
      int cmax = 0;
      for(int count: map.values()){
        cmax = count > cmax ? count : cmax;
      }
      same++;
      cmax += same;
      vertical += same;
      cmax = vertical > cmax ? vertical : cmax;
      max = cmax > max ? cmax : max;
      map.clear();
    }

    return max;
  }
}
