package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/3/16.
 */
public class MaxPointsOnALine {
  public int maxPoints(Point[] points) {
    if(points == null){
      return 0;
    }
    int len = points.length;
    if(len <= 2){
      return len;
    }
    Map<Double, Integer> map = new HashMap<Double, Integer>();
    int max = 2;

    int vertical = 0;
    int same = 0;
    for(int i = 0; i < len - 1; i++){
      vertical = 0;
      same = 0;
      for(int j = i + 1; j < len; j++){
        Point pi = points[i];
        Point pj = points[j];
        if(pi.x == pj.x){
          if(pi.y == pj.y){
            same++;
          }else{
            vertical++;
          }
        }else{
          Double slope = ((double)(pj.y - pi.y))/(pj.x - pi.x);
          slope = slope == -0.0 ? 0.0 : slope;
          if(map.containsKey(slope)){
            int count = map.get(slope);
            count++;
            map.put(slope, count);
          }else{
            map.put(slope, 2);
          }
        }
      }

      for(int count: map.values()){
        max = (count + same) > max ? (count + same) : max;
      }

      int vCount = vertical + same + 1;
      max = (vCount > max ? vCount : max);
      map.clear();
    }
    return max;
  }
}
