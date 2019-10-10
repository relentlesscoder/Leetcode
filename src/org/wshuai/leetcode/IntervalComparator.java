package org.wshuai.leetcode;

import java.util.Comparator;

/**
 * Created by Wei on 11/13/16.
 */
public class IntervalComparator implements Comparator<Interval> {
	@Override
	public int compare(Interval x, Interval y) {
		return x.start != y.start ? x.start - y.start : x.end - y.end;
	}
}
