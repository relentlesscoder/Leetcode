package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Wei on 11/12/16.
 * #406 https://leetcode.com/problems/queue-reconstruction-by-height/
 */
public class QueueReconstructionByHeight {
  public int[][] reconstructQueue(int[][] people) {
    if(people == null || people.length == 0){
      return people;
    }
    int len = people.length;
    List<int[]> tmp = new ArrayList<int[]>();
    Arrays.sort(people, new HeightComparator());
    for(int[] p: people){
      tmp.add(p[1], p);
    }
    int i = 0;
    for(int[] p: tmp){
      people[i++] = p;
    }
    return people;
  }

  class HeightComparator implements Comparator<int[]>{
    @Override
    public int compare(int[] x, int[] y){
      return y[0] != x[0] ? y[0]-x[0] : x[1]-y[1];
    }
  }
}
