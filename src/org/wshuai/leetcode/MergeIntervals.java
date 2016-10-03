package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Wei on 10/1/2016.
 */
public class MergeIntervals {
  public List<Interval> merge(List<Interval> intervals) {
    List<Interval> lst = new ArrayList<Interval>();
    if(intervals == null || intervals.size() == 0){
      return lst;
    }
    int len = intervals.size();
    //quickSortInterval(intervals, 0, intervals.size() - 1);
    Collections.sort(intervals, ORDER);
    lst.add(intervals.get(0));
    int j = 0;
    for(int i = 1; i < len; i++){
      Interval i1 = lst.get(j);
      Interval i2 = intervals.get(i);
      if(i1.end < i2.start){
        j++;
        lst.add(i2);
      }else if(i1.end <= i2.end){
        Interval i3 = new Interval(i1.start, i2.end);
        lst.set(j, i3);
      }else{
        Interval i4 = new Interval(i1.start, i1.end);
        lst.set(j, i4);
      }
    }

    return lst;
  }

  static final Comparator<Interval> ORDER = new Comparator<Interval>(){
    public int compare(Interval i1, Interval i2){
      return i1.start - i2.start;
    }
  };

  private void quickSortInterval(List<Interval> intervals, int p, int q){
    if(p < q){
      int r = partition(intervals, p, q);
      quickSortInterval(intervals, p, r - 1);
      quickSortInterval(intervals, r + 1, q);
    }
  }

  private int partition(List<Interval> intervals, int p, int q){
    int pivot = q;
    int i = p;
    Interval pi = intervals.get(pivot);
    for(int j = p; j < q; j++){
      Interval curr = intervals.get(j);
      if(curr.start <= pi.start){
        intervals.set(j, intervals.get(i));
        intervals.set(i, curr);
        i++;
      }
    }

    intervals.set(pivot, intervals.get(i));
    intervals.set(i, pi);
    return i;
  }
}
