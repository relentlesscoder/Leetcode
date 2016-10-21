package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/3/16.
 * #57 https://leetcode.com/problems/insert-interval/
 */
public class InsertInterval {

  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    if(newInterval == null){
      return intervals;
    }
    List<Interval> lst = new ArrayList<Interval>();
    lst.add(newInterval);
    if(intervals == null || intervals.size() == 0){
      return lst;
    }

    int i = 0;
    int len = intervals.size();
    while(i < len){
      Interval curr = intervals.get(i);
      int size = lst.size();
      Interval tail = lst.get(size - 1);
      if(curr.end < tail.start){
        lst.set(size - 1, curr);
        lst.add(tail);
      }else if(curr.start > tail.end){
        lst.add(curr);
      }else{
        Interval ni = new Interval(Math.min(curr.start, tail.start),
          Math.max(curr.end, tail.end));
        lst.set(size - 1, ni);
      }
      i++;
    }

    return lst;
  }
}
