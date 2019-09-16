package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.EmployeeFreeTime;
import org.wshuai.leetcode.Interval;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFreeTimeTest {
  @Test
  public void testcase(){
    EmployeeFreeTime ef = new EmployeeFreeTime();
    List<List<Interval>> all = new ArrayList<>();
    List<Interval> first = new ArrayList<>();
    first.add(new Interval(1, 2));
    first.add(new Interval(5, 6));
    List<Interval> second = new ArrayList<>();
    second.add(new Interval(1, 3));
    List<Interval> third = new ArrayList<>();
    third.add(new Interval(4, 10));
    all.add(first);
    all.add(second);
    all.add(third);
    List<Interval> res = ef.employeeFreeTime(all);
  }
}
