package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.Interval;
import org.wshuai.leetcode.MergeIntervals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/1/2016.
 */
public class MergeIntervalsTest {
  @Test
  public void testcase1(){
    MergeIntervals mi = new MergeIntervals();
    List<Interval> intervals = new ArrayList<Interval>();
    intervals.add(new Interval(2, 3));
    intervals.add(new Interval(4, 5));
    intervals.add(new Interval(6, 7));
    intervals.add(new Interval(8, 9));
    intervals.add(new Interval(1, 10));
    mi.merge(intervals);
  }

  @Test
  public void testcase2(){
    MergeIntervals mi = new MergeIntervals();
    List<Interval> intervals = new ArrayList<Interval>();
    intervals.add(new Interval(1, 4));
    intervals.add(new Interval(5, 6));
    mi.merge(intervals);
  }
}
