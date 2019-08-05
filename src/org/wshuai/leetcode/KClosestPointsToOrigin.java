package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 8/4/19.
 * #973 https://leetcode.com/problems/k-closest-points-to-origin/
 */
public class KClosestPointsToOrigin {

  public int[][] kClosest(int[][] points, int K) {
    int len = points.length;
    int[] dists = new int[len];
    for(int i = 0; i < len; i++){
      dists[i] = points[i][0]*points[i][0] + points[i][1]*points[i][1];
    }

    Arrays.sort(dists);
    int kDist = dists[K-1];
    int[][] res = new int[K][2];
    int j = 0;
    for(int i = 0; i < len; i++){
      int dist = points[i][0]*points[i][0] + points[i][1]*points[i][1];
      if(dist <= kDist){
        res[j][0] = points[i][0];
        res[j][1] = points[i][1];
        j++;
      }
    }
    return res;
  }

  public int[][] kClosestPriorityQueue(int[][] points, int K) {
    PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    for(int i = 0; i < points.length; i++){
      int sum = points[i][0]*points[i][0];
      sum += points[i][1]*points[i][1];
      queue.offer(sum);
      if(map.containsKey(sum)){
        List<Integer> lst = map.get(sum);
        lst.add(i);
      }else{
        List<Integer> lst = new ArrayList<Integer>();
        lst.add(i);
        map.put(sum, lst);
      }
    }
    int[][] result = new int[K][2];
    int j = 0;
    while(K > 0){
      int sum = queue.poll();
      List<Integer> lst = map.get(sum);
      int len = lst.size();
      for(int i = 0; i < len; i++){
        int pos = lst.get(i);
        result[j][0] = points[pos][0];
        result[j][1] = points[pos][1];
        j++;
        K--;
      }
    }
    return result;
  }
}
