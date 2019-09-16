package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 9/15/2019.
 * #759 https://leetcode.com/problems/employee-free-time/
 */
public class EmployeeFreeTime {
  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    List<Interval> res = new ArrayList<>();
    PriorityQueue<Interval> queue = new PriorityQueue<>((a, b) -> a.start == b.start ? a.end - b.end : a.start - b.start);
    for(List<Interval> lst: schedule){
      for(Interval i: lst){
        queue.offer(i);
      }
    }
    Interval curr = queue.poll();
    if(curr == null){
      return res;
    }
    while(!queue.isEmpty()){
      Interval next = queue.poll();
      if(curr.start == next.start && curr.end == next.end){
        continue;
      }
      if(curr.end < next.start){
        res.add(new Interval(curr.end, next.start));
        curr = next;
      }else{
        curr = new Interval(curr.start, Math.max(next.end, curr.end));
      }
    }
    return res;
  }
}
